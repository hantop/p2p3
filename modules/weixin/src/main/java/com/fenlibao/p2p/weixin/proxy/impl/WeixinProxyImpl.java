package com.fenlibao.p2p.weixin.proxy.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fenlibao.p2p.common.http.HttpClientUtil;
import com.fenlibao.p2p.weixin.config.WeixinConfig;
import com.fenlibao.p2p.weixin.domain.*;
import com.fenlibao.p2p.weixin.exception.WeixinException;
import com.fenlibao.p2p.weixin.message.Message;
import com.fenlibao.p2p.weixin.message.req.ReqTicket;
import com.fenlibao.p2p.weixin.proxy.WeixinProxy;
import com.fenlibao.p2p.weixin.service.TicketService;
import com.fenlibao.p2p.weixin.service.TokenService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2015/6/10.
 */
@Component("weixinProxy")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class WeixinProxyImpl implements WeixinProxy, ApplicationListener<ContextRefreshedEvent>,ApplicationContextAware {


    @Autowired
    private WeixinConfig weixinConfig;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TicketService ticketService;

    private Token token;

    private Ticket ticket;

    private WeixinProxy weixinProxy;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Token token = weixinProxy.httpToken();
    }

    @Override
    public String getTokenKey() {
        return weixinConfig.getTokenKey();
    }

    @Override
    public Token httpToken() {
        return httpToken(TokeyType.ACCESS_TOKEN);
    }

    /**
     * 根据token类型获取，率先查询数据库，如果数据库有改token，检验时间是否过期，如果时间未过期，则返回，如果时间已经过期，重新生成token并存储到数据库
     *
     * @param tokenType
     * @return
     */
    private Token httpToken(TokeyType tokenType) {
        if (this.token == null) {
            this.token = this.tokenService.selectLast(tokenType);
        }
        if (token != null && token.getExpiresIn() != null) {
            Long currentTime = System.currentTimeMillis() / 1000;
            Long lastTokenCreateTime = token.getCreateTime() / 1000;
            Long gap = currentTime - lastTokenCreateTime;
            if (gap < token.getExpiresIn()) {
                return token;
            }
        }
        String tokenUrl = String.format(TOKEN, weixinConfig.getAppId(), weixinConfig.getAppSecret());
        this.token = httpToken(tokenUrl, tokenType);

        return this.token;
    }

    /**
     * 请求服务器access_token
     *
     * @return
     */
    private Token httpToken(String url, TokeyType type) {
        byte[] result = HttpClientUtil.httpGet(url);
        Token token = JSON.parseObject(result, Token.class);
        token.setType(type);
        return token;
    }

    @Override
    public Fans httpFans(String url) {
        byte[] results = HttpClientUtil.httpGet(url);
        Fans fans = JSON.parseObject(results, Fans.class);
        return fans;
    }

    @Override
    public Ticket httpTicket(ReqTicket reqTicket) {

        Token token = weixinProxy.httpToken();
        String ticketUrl = String.format(TICKET, token.getAccessToken());
        String jsonParam = JSON.toJSONString(reqTicket, SerializerFeature.WriteNonStringKeyAsString);
        byte[] results = HttpClientUtil.httpPost(ticketUrl, jsonParam);
        Ticket ticket = JSON.parseObject(results, Ticket.class);

        ticket.setType(TicketType.QR_TICKET);
        ticket.setCreateTime(System.currentTimeMillis());
        return ticket;
    }

    @Override
    public Ticket httpTicket() {
        if(this.ticket == null) {
            this.ticket = this.ticketService.selectLastTicket(TicketType.JSAPI_TICKET);
        }
        if(ticket != null && ticket.getCreateTime() != null) {
            Long currentTime = System.currentTimeMillis() / 1000;
            Long lastTokenCreateTime = ticket.getCreateTime() / 1000;
            Long gap = currentTime - lastTokenCreateTime;
            if (gap < ticket.getExpiresIn()) {
                return ticket;
            }
        }
        String ticketUrl = String.format(JSAPI_TICKET, this.weixinProxy.httpToken().getAccessToken());
        byte[] bytes = HttpClientUtil.httpGet(ticketUrl);
        this.ticket = JSON.parseObject(bytes, Ticket.class);
        ticket.setType(TicketType.JSAPI_TICKET);
        ticket.setCreateTime(System.currentTimeMillis());
        return ticket;
    }

    @Override
    public Token httpToken(String code) throws WeixinException {
        //获取access_token
        String oauth2TokenUrl = String.format(OAUTH2_TOKEN, weixinConfig.getAppId(), weixinConfig.getAppSecret(), code);
        //网页的access_token
        Token oauth2Token = httpToken(oauth2TokenUrl, TokeyType.OAUTH2_ACCESS_TOKEN);

        //刷新access_token（如果需要）
//        String refreshTokenUrl = String.format(REFRESH_TOKEN, weixinConfig.getAppId(),oauth2Token.getRefreshToken());
//        byte[] bytes = HttpClientUtil.httpGet(refreshTokenUrl);
//        oauth2Token = JSON.parseObject(bytes, Token.class);
//        if(oauth2Token.getErrcode() == HTTP_STATUS.ERROR_CODE_40029.getErrorcode()) {
//            new RuntimeException(HTTP_STATUS.ERROR_CODE_40029.getErrmsg());
//        }
        return oauth2Token;
    }

    @Override
    public Qrcode httpQrcode(ReqTicket reqTicket,String scene) {
        String sceneStr = reqTicket.generateSceneStr();
        Integer sceneId = reqTicket.generateSceneId();
        String actionName = reqTicket.getActionName();

        Ticket ticket = weixinProxy.httpTicket(reqTicket);//通过参数获取ticket
        String qrcodeUrl = String.format(QRCODE, ticket.getTicket());
        byte[] results = HttpClientUtil.httpGet(qrcodeUrl);
        Qrcode qrcode = new Qrcode();
        qrcode.setTicketId(ticket.getId());
        qrcode.setActionName(actionName);
        qrcode.setSceneId(sceneId);
        qrcode.setSceneStr(sceneStr);
        qrcode.setName(weixinConfig.getName());
        qrcode.setSuffix(weixinConfig.getSuffix());
        qrcode.setBytes(results);
        qrcode.setSceneName(scene);
        return qrcode;
    }

    @Override
    public Message httpTemplateMsg(String templateMsg) {
        Token token = this.weixinProxy.httpToken();
        String templateUrl = String.format(TEMPLATE,token.getAccessToken());
        byte[] bytes = HttpClientUtil.httpPost(templateUrl,templateMsg);
        Message message = JSON.parseObject(bytes, Message.class);
        return message;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.weixinProxy = applicationContext.getBean(WeixinProxy.class);
    }
}

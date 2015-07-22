package com.fenlibao.p2p.weixin.proxy.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fenlibao.p2p.common.http.HttpClientUtil;
import com.fenlibao.p2p.constant.util.Utils;
import com.fenlibao.p2p.weixin.config.WeixinConfig;
import com.fenlibao.p2p.weixin.domain.Fans;
import com.fenlibao.p2p.weixin.domain.Qrcode;
import com.fenlibao.p2p.weixin.domain.Ticket;
import com.fenlibao.p2p.weixin.domain.Token;
import com.fenlibao.p2p.weixin.exception.WeixinException;
import com.fenlibao.p2p.weixin.message.Message;
import com.fenlibao.p2p.weixin.message.Poi;
import com.fenlibao.p2p.weixin.message.WxMsg;
import com.fenlibao.p2p.weixin.message.card.Card;
import com.fenlibao.p2p.weixin.message.card.UserCard;
import com.fenlibao.p2p.weixin.message.card.req.ReqBatchCatch;
import com.fenlibao.p2p.weixin.message.card.req.ReqUserCard;
import com.fenlibao.p2p.weixin.message.req.ReqTicket;
import com.fenlibao.p2p.weixin.message.req.White;
import com.fenlibao.p2p.weixin.message.template.TemplateMsg;
import com.fenlibao.p2p.weixin.proxy.WeixinProxy;
import com.fenlibao.p2p.weixin.service.TicketService;
import com.fenlibao.p2p.weixin.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Administrator on 2015/6/10.
 */
@Component("weixinProxy")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class WeixinProxyImpl implements WeixinProxy, ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(WeixinProxyImpl.class);
    @Inject
    private WeixinConfig weixinConfig;

    @Inject
    private TokenService tokenService;

    @Inject
    private TicketService ticketService;

    private Token token;

    private Ticket jssapiTicket;//jssapi网页ticket

    private Ticket jssapiCardTicket;//卡券网页ticket

    private WeixinProxy weixinProxy;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //防止重复执行。
        if (event.getApplicationContext().getParent() == null) {
            this.token = weixinProxy.httpToken();
        }
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
        if (log.isInfoEnabled()) {
            log.info("请求token:{}", JSON.toJSONString(tokenType, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
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
        String tokenUrl = String.format(TOKEN_URL, weixinConfig.getAppId(), weixinConfig.getAppSecret());
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
        if (log.isInfoEnabled()) {
            log.info(JSON.toJSONString(reqTicket, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        Token token = weixinProxy.httpToken();
        String ticketUrl = null;
        TicketType ticketType = null;
        if (reqTicket.getActionName() == QrcodeType.QR_CARD) {
            ticketUrl = String.format(CARD_QRCODE_URL, token.getAccessToken());//卡券投放的二维码ticket路径
            ticketType = TicketType.QR_CARD_TICKET;
        } else {
            ticketUrl = String.format(TICKET_URL, token.getAccessToken());//二维码ticket路径
            ticketType = TicketType.QR_TICKET;
        }
        Assert.notNull(ticketUrl, "ticket url 不能为空");

        String jsonParam = JSON.toJSONString(reqTicket, SerializerFeature.WriteNonStringKeyAsString);
        byte[] results = HttpClientUtil.httpPost(ticketUrl, jsonParam);
        Ticket ticket = JSON.parseObject(results, Ticket.class);

        ticket.setType(ticketType);
        ticket.setCreateTime(System.currentTimeMillis());
        if (reqTicket.getActionName() == QrcodeType.QR_CARD) {
            ticket.setCode(reqTicket.getCode());
            ticket.setCardId(reqTicket.getCardId());
            ticket.setOpenid(reqTicket.getOpenid());
            ticket.setUniqueCode(reqTicket.getUniqueCode());
            ticket.setOuterId(reqTicket.getOuterId());
        }

        if (reqTicket.getActionName() == QrcodeType.QR_CARD) {

        }
        return ticket;
    }

    @Override
    public Ticket httpTicket(TicketType ticketType) {
        Assert.notNull(ticketType, "请求的ticketType类型不能为空");
        if (log.isInfoEnabled()) {
            log.info("请求获取ticket：{}", JSON.toJSONString(ticketType, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        if (ticketType == TicketType.QR_TICKET || ticketType == TicketType.QR_CARD_TICKET) {
            if (log.isErrorEnabled()) {
                log.error("不能获取改类型{}的ticket，请调用public Ticket httpTicket(ReqTicket reqTicket) 方法获取", ticketType);
            }
            throw new RuntimeException("不能获取改类型(" + ticketType + ")的ticket");
        }
        Ticket result = null;
        if (ticketType == TicketType.JSAPI_TICKET) {
            result = this.jssapiTicket;
        } else if (ticketType == TicketType.JSAPI_CARD_TICKET) {
            result = this.jssapiCardTicket;
        }
        if (result == null) {
            result = this.ticketService.selectLastTicket(ticketType);
        }
        if (result != null && result.getCreateTime() != null) {
            Long currentTime = System.currentTimeMillis() / 1000;
            Long lastTokenCreateTime = result.getCreateTime() / 1000;
            Long gap = currentTime - lastTokenCreateTime;
            if (gap < result.getExpiresIn()) {

                if (ticketType == TicketType.JSAPI_TICKET) {
                    this.jssapiTicket = result;
                } else if (ticketType == TicketType.JSAPI_CARD_TICKET) {
                    this.jssapiCardTicket = result;
                }
                if (log.isInfoEnabled()) {
                    log.info("返回ticket:{}", JSON.toJSONString(result, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
                }
                return result;
            }
        }
        String ticketUrl = null;

        if (ticketType == TicketType.JSAPI_TICKET) {
            ticketUrl = String.format(JSAPI_TICKET_URL, this.weixinProxy.httpToken().getAccessToken());
        } else if (ticketType == TicketType.JSAPI_CARD_TICKET) {
            ticketUrl = String.format(JSAPI_CARD_TICKET_URL, this.weixinProxy.httpToken().getAccessToken());
        }
        Assert.notNull(ticketUrl, "获取ticket的url不能为空");

        byte[] bytes = HttpClientUtil.httpGet(ticketUrl);
        result = JSON.parseObject(bytes, Ticket.class);
        result.setType(ticketType);
        result.setCreateTime(System.currentTimeMillis());
        if (log.isInfoEnabled()) {
            log.info("返回ticket:{}", JSON.toJSONString(result, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        if (ticketType == TicketType.JSAPI_TICKET) {
            this.jssapiTicket = result;
        } else if (ticketType == TicketType.JSAPI_CARD_TICKET) {
            this.jssapiCardTicket = result;
        }
        return result;

    }

    @Override
    public Token httpToken(String code) throws WeixinException {
        //获取access_token
        String oauth2TokenUrl = String.format(OAUTH2_TOKEN_URL, weixinConfig.getAppId(), weixinConfig.getAppSecret(), code);
        //网页的access_token
        Token oauth2Token = httpToken(oauth2TokenUrl, TokeyType.OAUTH2_ACCESS_TOKEN);

        //刷新access_token（如果需要）
//        String refreshTokenUrl = String.format(REFRESH_TOKEN, weixinConfig.getAppId(),oauth2Token.getRefreshToken());
//        byte[] bytes = HttpClientUtil.httpGet(refreshTokenUrl);
//        oauth2Token = JSON.parseObject(bytes, Token.class);
//        if(oauth2Token.getErrcode() == HTTP_STATUS.ERROR_CODE_40029.getErrorcode()) {
//            new RuntimeException(HTTP_STATUS.ERROR_CODE_40029.getErrmsg());
//        }
        if (log.isInfoEnabled()) {
            log.info("返回oauth2Token信息:{}", JSON.toJSONString(oauth2Token, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        return oauth2Token;
    }

    @Override
    public Qrcode httpQrcode(ReqTicket reqTicket, String scene) {
        Serializable sceneValue = reqTicket.generateScene();
        String actionName = reqTicket.getActionName().toString();

        Ticket ticket = weixinProxy.httpTicket(reqTicket);//通过参数获取ticket
        String qrcodeUrl = String.format(QRCODE_URL, ticket.getTicket());
        byte[] results = HttpClientUtil.httpGet(qrcodeUrl);
        Qrcode qrcode = new Qrcode();
        qrcode.setTicketId(ticket.getId());
        qrcode.setActionName(actionName);
        qrcode.setSceneValue(sceneValue.toString());
        qrcode.setSceneType(sceneValue.getClass().getName());
        qrcode.setName(weixinConfig.getName());
        qrcode.setSuffix(weixinConfig.getSuffix());
        qrcode.setBytes(results);
        qrcode.setSceneName(scene);
        if (log.isInfoEnabled()) {
            log.info("返回二维码信息:{}", JSON.toJSONString(qrcode, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        return qrcode;
    }

    @Override
    public Message httpTemplateMsg(TemplateMsg templateMsg) {
        Token token = this.weixinProxy.httpToken();
        String templateUrl = String.format(TEMPLATE_URL, token.getAccessToken());
        String req = JSON.toJSONString(templateMsg);
        byte[] bytes = HttpClientUtil.httpPost(templateUrl, req);
        Message message = JSON.parseObject(bytes, Message.class);
        if (log.isInfoEnabled()) {
            log.info("返回模板消息信息:{}", JSON.toJSONString(message, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        return message;
    }

    @Override
    public Poi httpPoi(String poiId) {
        Assert.notNull(poiId, "获取门店的poi_id不能为空");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("poi_id", poiId);
        String req = jsonObject.toJSONString();
        String url = String.format(POI_URL, this.weixinProxy.httpToken().getAccessToken());
        byte[] bytes = HttpClientUtil.httpPost(url, req);
        Poi poi = JSON.parseObject(bytes, Poi.class);
        if (log.isInfoEnabled()) {
            log.info("返回门店信息:{}", JSON.toJSONString(poi, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        return poi;
    }

    @Override
    public WxMsg testwhitelist(White white) {
        String jsonString = JSON.toJSONString(white);
        String url = String.format(CARD_TESTWHITELIST_URL, this.weixinProxy.httpToken().getAccessToken());
        byte[] bytes = HttpClientUtil.httpPost(url, jsonString);
        return JSON.parseObject(bytes, WxMsg.class);
    }

    @Override
    public UserCard getUserCardList(ReqUserCard userCard) {
        Utils.validate(userCard);
        String jsonString = JSON.toJSONString(userCard);
        String token = this.weixinProxy.httpToken().getAccessToken();
        String url = String.format(USER_CARD_LIST_URL, token);
        byte[] bytes = HttpClientUtil.httpPost(url, jsonString);
        return JSON.parseObject(bytes, UserCard.class);
    }

    @Override
    public Card getCard(JSONObject params) throws IOException {
        Object cardId = params.get("card_id");
        Assert.notNull(cardId, "卡券ID不能为空");
        String jsonString = params.toString();
        String token = this.weixinProxy.httpToken().getAccessToken();
        String url = String.format(CARD_URL, token);
        byte[] bytes = HttpClientUtil.httpPost(url, jsonString);
        return JSON.parseObject(bytes, Card.class);
    }

    @Override
    public Card batchCard(ReqBatchCatch reqBatchCatch) {
        Utils.validate(reqBatchCatch);
        String jsonString = reqBatchCatch.toString();
        String token = this.weixinProxy.httpToken().getAccessToken();
        String url = String.format(BATCH_CARD_URL, token);
        byte[] bytes = HttpClientUtil.httpPost(url, jsonString);
        return JSON.parseObject(bytes, Card.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.weixinProxy = applicationContext.getBean(WeixinProxy.class);
    }
}

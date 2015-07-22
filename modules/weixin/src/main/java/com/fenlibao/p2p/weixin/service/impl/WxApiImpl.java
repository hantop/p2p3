package com.fenlibao.p2p.weixin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fenlibao.p2p.weixin.config.WeixinConfig;
import com.fenlibao.p2p.weixin.defines.*;
import com.fenlibao.p2p.weixin.domain.Fans;
import com.fenlibao.p2p.weixin.domain.Qrcode;
import com.fenlibao.p2p.weixin.domain.Ticket;
import com.fenlibao.p2p.weixin.domain.Token;
import com.fenlibao.p2p.weixin.event.FansEvent;
import com.fenlibao.p2p.weixin.event.MsgEvent;
import com.fenlibao.p2p.weixin.event.PoiCheckEvent;
import com.fenlibao.p2p.weixin.exception.WeixinException;
import com.fenlibao.p2p.weixin.message.Message;
import com.fenlibao.p2p.weixin.message.card.Card;
import com.fenlibao.p2p.weixin.message.card.CardTypeValue;
import com.fenlibao.p2p.weixin.message.card.UserCard;
import com.fenlibao.p2p.weixin.message.card.req.ReqBatchCatch;
import com.fenlibao.p2p.weixin.message.card.req.ReqUserCard;
import com.fenlibao.p2p.weixin.message.req.ReqTicket;
import com.fenlibao.p2p.weixin.message.template.TemplateMsg;
import com.fenlibao.p2p.weixin.proxy.WeixinProxy;
import com.fenlibao.p2p.weixin.service.MessageHandler;
import com.fenlibao.p2p.weixin.service.QrcodeService;
import com.fenlibao.p2p.weixin.service.WxApi;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by Administrator on 2015/7/6.
 */
@Service("wxApi")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class WxApiImpl implements WxApi, ApplicationListener<ContextRefreshedEvent> {

    private final static Logger log = LoggerFactory.getLogger(WxApiImpl.class);

    private static final XStream xStream = new XStream();

    @Inject
    private QrcodeService qrcodeService;

    @NotNull
    @Inject
    private WeixinConfig weixinConfig;

    @Inject
    private WeixinProxy weixinProxy;

    @Inject
    private ApplicationEventPublisher publisher;

    private MessageHandler messageHandler;

    @Inject
    public WxApiImpl(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    static {
        xStream.autodetectAnnotations(true);
        xStream.processAnnotations(Message.class);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        //防止重复执行。
//        if (event.getApplicationContext().getParent() == null) {
//            String appid = "o5D9Ts8qEfQy73VwwTOeUbG34Sfw";
//            try {
//                Fans fans = this.getFans(appid);
//                System.out.println(fans);
//            } catch (WeixinException e) {
//                e.printStackTrace();
//            }
//        }
    }


    @Override
    public WeixinConfig getWeixinConfig() {
        return weixinConfig;
    }

    @Override
    public String generateOauth2Url(String url, String state) {
        url = URLEncoder.encode(url);
        return String.format(OAUTH2_ACCESS_TOKEN, weixinConfig.getAppId(), url, SnsapiScope.snsapi_userinfo, state);
    }

    /**
     * 在开发者首次提交验证申请时，微信服务器将发送GET请求到填写的URL上，并且带上四个参数（signature、timestamp、nonce、echostr），开发者通过对签名（即signature）的效验，来判断此条消息的真实性。
     * <p/>
     * 此后，每次开发者接收用户消息的时候，微信也都会带上前面三个参数（signature、timestamp、nonce）访问开发者设置的URL，开发者依然通过对签名的效验判断此条消息的真实性。效验方式与首次提交验证申请一致。
     * <p/>
     * 参数	描述
     * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * timestamp	时间戳
     * nonce	随机数
     * echostr	随机字符串
     * 开发者通过检验signature对请求进行校验（下面有校验方式）。若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。
     * <p/>
     * 加密/校验流程如下：
     * 1. 将token、timestamp、nonce三个参数进行字典序排序
     * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @Override
    public boolean checkSignature(String signature, String timestamp, String nonce, String echostr) {
        String[] arr = new String[]{weixinProxy.getTokenKey(), timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        String content = Arrays.asList(arr).toString().replaceAll(",", "").replaceAll("\\s*", "");
        content = content.substring(1, content.length() - 1);
        String tmpStr = DigestUtils.sha1Hex(content);
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        boolean flag = tmpStr != null ? tmpStr.equals(signature) : false;
        if (log.isInfoEnabled()) {
            log.info("验证消息真实性 signature:" + signature + ",timestamp:" + timestamp + ",nonce:" + nonce + ",echostr:" + echostr + ",验证结果字符串：" + tmpStr + ",返回结果：" + flag);
        }
        return flag;
    }

    @Override
    public Map<String, String> signature(String url) {
        Map<String, String> ret = new HashMap<>();
        Ticket ticket = this.weixinProxy.httpTicket(TicketType.JSAPI_TICKET);
        String nonceStr = this.createNonceStr();
        String timestamp = this.createTimestamp();
        String signature = String.format(JSAPI_SIGN_URL, ticket.getTicket(), nonceStr, timestamp, url);
        signature = DigestUtils.sha1Hex(signature);
        ret.put("url", url);
        ret.put("jsapi_ticket", ticket.getTicket());
        ret.put("nonceStr", nonceStr);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("appId", weixinConfig.getAppId());
        if (log.isInfoEnabled()) {
            log.info("微信网页授权返回信息:{}", JSON.toJSONString(ret, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        return ret;
    }

    @Override
    public Map<String, String> signature(CardTypeValue cardType, String cardId, String locationId) {
        Assert.notNull(cardType, "卡券类型不能为空");
        Map<String, String> ret = new HashMap<>();
        List<String> list = new ArrayList<>();
        Ticket ticket = this.weixinProxy.httpTicket(TicketType.JSAPI_CARD_TICKET);
        list.add(ticket.getTicket());
        list.add(weixinConfig.getAppId());
        if (locationId != null) {
            list.add(locationId);
        }
        if (cardType != null) {
            list.add(cardType.toString());
        }
        if (cardId != null) {
            list.add(cardId);
        }
        String timestamp = this.createTimestamp();
        String nonceStr = this.createNonceStr();
        list.add(timestamp);
        list.add(nonceStr);
        Collections.sort(list);
        String content = list.toString().replaceAll(",", "").replaceAll("\\s*", "");
        content = content.substring(1, content.length() - 1);
        String signature = DigestUtils.sha1Hex(content);

        ret.put("location_id", locationId);
        ret.put("time_stamp", timestamp);
        ret.put("nonce_str", nonceStr);
        ret.put("card_id", cardId);
        ret.put("card_type", cardType.toString());
        ret.put("signature", signature);
        ret.put("api_ticket", ticket.getTicket());
        ret.put("app_id", weixinConfig.getAppId());

        if (log.isInfoEnabled()) {
            log.info("卡券签名：{},\n signature:{}", JSON.toJSONString(ret));
        }
        return ret;
    }
    @Override
    public List<Map<String, Object>> signature(ReqBatchCatch reqBatchCatch, String openid, String code) {
        Card card = this.weixinProxy.batchCard(reqBatchCatch);
        List<String> cardsId = Arrays.asList(card.getCardIdList());
        return signature(cardsId, openid, code);
    }

    @Override
    public List<Map<String, Object>> signature(List<String> cardsId, String openid, String code) {
        Assert.notNull(cardsId, "待签名的卡券列表不能为空");
        Assert.notEmpty(cardsId, "待签名的卡券列表不能为空");
        List<Map<String, Object>> result = new ArrayList<>();
        String ticket = this.weixinProxy.httpTicket(TicketType.JSAPI_CARD_TICKET).getTicket();
        for (String cardId : cardsId) {
            Map<String, Object> cardSignature = new HashMap<>();
            Map<String, String> cardExt = new HashMap<>();
            String timestamp = this.createTimestamp();
            String nonceStr = this.createNonceStr();
            List<String> list = new ArrayList<>();
            list.add(cardId);
            list.add(ticket);
            list.add(timestamp);
            list.add(nonceStr);
            if (openid != null) {
                list.add(openid);
            }
            if (code != null) {
                list.add(code);
            }
            Collections.sort(list);
            String content = list.toString().replaceAll(",", "").replaceAll("\\s*", "");
            content = content.substring(1, content.length() - 1);
            String signature = DigestUtils.sha1Hex(content);
            cardExt.put("code", code == null ? "" : code);
            cardExt.put("openid", openid == null ? "" : openid);
            cardExt.put("timestamp", timestamp);
            cardExt.put("nonce_str", nonceStr);
            cardExt.put("signature", signature);
//            cardExt.put("ticket", ticket);
            cardSignature.put("cardExt", cardExt);
            cardSignature.put("cardId", cardId);
            result.add(cardSignature);
        }
        return result;
    }

    private String createNonceStr() {
        return UUID.randomUUID().toString();
    }

    private String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    @Override
    public Fans getFans(String openid) throws WeixinException {
        String fansUrl = String.format(FANS_URL, this.weixinProxy.httpToken().getAccessToken(), openid);
        return this.weixinProxy.httpFans(fansUrl);
    }

    @Override
    public Qrcode getQrLimitScene(String sceneStr, String scene) throws WeixinException {
        ReqTicket reqTicket = new ReqTicket(QrcodeType.QR_LIMIT_SCENE, sceneStr);
        return getLimitSceneQrcode(reqTicket, scene);
    }

    @Override
    public Qrcode getQrLimitScene(int sceneId, String scene) throws WeixinException {
        ReqTicket reqTicket = new ReqTicket(QrcodeType.QR_LIMIT_SCENE, sceneId);
        return getLimitSceneQrcode(reqTicket, scene);
    }

    @Override
    public Qrcode selectTempSceneBySceneId(int sceneId, String scene) throws WeixinException {
        Qrcode qrcode = null;//this.qrcodeService.selectTempSceneBySceneId(sceneId);
        if (qrcode != null)
            return qrcode;
        return getQrcode(new ReqTicket(QrcodeType.QR_SCENE, sceneId), scene);
    }

    /**
     * 网络请求获取二维码信息
     * 1.首先查询数据库是否有二维码，
     * 2.网络获取二维码
     *
     * @param reqTicket
     * @return
     */
    private Qrcode getLimitSceneQrcode(ReqTicket reqTicket, String scene) {
        Serializable sceneValue = reqTicket.generateScene();
        if (sceneValue != null) {
            Qrcode qrcode = this.qrcodeService.selectLimitSceneByScene(sceneValue, scene);
            if (qrcode != null)
                return qrcode;
        }
        return getQrcode(reqTicket, scene);
    }

    /**
     * 根据参数请求获取二维码信息
     *
     * @param reqTicket
     * @return
     */
    private Qrcode getQrcode(ReqTicket reqTicket, String scene) {
        return this.weixinProxy.httpQrcode(reqTicket, scene);
    }

    @Override
    public OauthDefines oauth2(String code, String state) {
        Token oauth2Token = null;
        try {
            oauth2Token = this.weixinProxy.httpToken(code);
        } catch (WeixinException e) {
            if (log.isInfoEnabled()) {
                log.info(e.getMessage());
            }
            OauthDefines oauthDefines = new OauthDefines(CodeMsg.ERROR_TIME_OUT);
            if (log.isErrorEnabled()) {
                log.info("网页授权错误:{}", JSON.toJSONString(oauthDefines, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
            }
            return oauthDefines;
        }
        //拉取用户信息(需scope为 snsapi_userinfo)
        String oauth2UserInfoUrl = String.format(SNSAPI_USERINFO_URL, oauth2Token.getAccessToken(), oauth2Token.getOpenid());
        Fans userInfo = this.weixinProxy.httpFans(oauth2UserInfoUrl);
        if (log.isInfoEnabled()) {
            log.info("获取用户信息:{}", JSON.toJSONString(userInfo, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        this.messageHandler.oauth2Event(userInfo, state);
        OauthDefines oauthDefines = new OauthDefines(CodeMsg.SUCCESS, userInfo.getOpenid());
        return oauthDefines;
    }

    @Override
    public Serializable process(String reqMsg, String host) {
        if (log.isInfoEnabled()) {
            log.info("请求消息:{},\n请求域名：{}", reqMsg, host);
        }
        Message message = (Message) xStream.fromXML(reqMsg);
        publisher.publishEvent(new MsgEvent(this, message, this.weixinConfig.getAppId(), MsgDefines.RECEIVE, reqMsg));
        Serializable result = process(message, host);
        if (log.isInfoEnabled()) {
            log.info("返回到用户消息:{}", JSON.toJSONString(result, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        return result;
    }

    @Override
    public Message send(TemplateMsg templateMsg) throws WeixinException {
        if (log.isInfoEnabled()) {
            log.info("发送模板消息:{}", JSON.toJSONString(templateMsg, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        Message result = this.weixinProxy.httpTemplateMsg(templateMsg);
        if (log.isInfoEnabled()) {
            log.info("返回模板消息:{}", JSON.toJSONString(result, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        return result;
    }

    @Override
    public UserCard getUserCardList(ReqUserCard params) {
        return this.weixinProxy.getUserCardList(params);
    }

    private Serializable process(Message message, String host) {
        Message respMsg = null;
        if (message.getMsgType().equals(MsgType.MESSAGETYPE_TEXT.toString())) {
            //图文消息
        } else if (message.getMsgType().equals(MsgType.MESSAGETYPE_IMAGE.toString())) {
            //图片消息
        } else if (message.getMsgType().equals(MsgType.MESSAGETYPE_VOICE.toString())) {
            //语音消息
        } else if (message.getMsgType().equals(MsgType.MESSAGETYPE_VIDEO.toString())) {
            //视频消息
        } else if (message.getMsgType().equals(MsgType.MESSAGETYPE_SHORTVIDEO.toString())) {
            //小视频消息
        } else if (message.getMsgType().equals(MsgType.MESSAGETYPE_LOCATION.toString())) {
            //地理位置消息
        } else if (message.getMsgType().equals(MsgType.MESSAGETYPE_MUSIC.toString())) {
            //音乐消息
        } else if (message.getMsgType().equals(MsgType.MESSAGETYPE_LINK.toString())) {
            //链接消息
        } else if (message.getMsgType().equals(MsgType.MESSAGETYPE_EVENT.toString())) {
            //事件推送
            if (message.getEvent() != null) {
                String event = message.getEvent();
                String eventKey = message.getEventKey();
                if ((event.equals(Event.EVENT_SCAN.toString())) || (eventKey != null && eventKey.startsWith(Event.EVENT_KEY_QRSCENE.toString()) && event.equals(Event.EVENT_SUBSCRIBE.toString()))) {
                    /**用户扫描事件*/
                    respMsg = this.messageHandler.scanEvent(message, this, host);
                } else if (event.equals(Event.EVENT_SUBSCRIBE.toString())) {
                    /**用户关注事件*/
                } else if (event.equals(Event.EVENT_UNSUBSCRIBE.toString())) {
                    /**用户取消关注事件*/
                } else if (event.equals(Event.EVENT_LOCATION.toString())) {
                    /**上报地理位置事件*/
                } else if (event.equals(Event.EVENT_CLICK)) {
                    /**用户点击自定义菜单后，微信会把点击事件推送给开发者，请注意，点击菜单弹出子菜单，不会产生上报。*/
                } else if (event.equals(Event.EVENT_VIEW.toString())) {
                    /**点击菜单跳转链接时的事件推送*/
                } else if (event.equals(Event.EVENT_POI_CHECK_NOTIFY.toString())) {
                    /**门店审核事件推送*/
                    publisher.publishEvent(new PoiCheckEvent(this, message));
                } else if (event.equals(Event.CARD_PASS_CHECK.toString())) {
                    /**卡券通过审核*/
                } else if (event.equals(Event.CARD_NOT_PASS_CHECK.toString())) {
                    /**卡券未通过审核*/
                } else if (event.equals(Event.USER_GET_CARD.toString())) {
                    /**领取事件推送*/

                } else if (event.equals(Event.USER_DEL_CARD.toString())) {
                    /**删除事件推送*/
                } else if (event.equals(Event.USER_CONSUME_CARD.toString())) {
                    /**核销事件*/
                } else if (event.equals(Event.USER_VIEW_CARD.toString())) {
                    /**核销事件，进入会员卡事件推送*/
                } else if (event.equals(Event.USER_ENTER_SESSION_FROM_CARD.toString())) {
                    /**从卡券进入公众号会话事件推送*/
                }
            }
        }
        String openId = message.getFromUserName();
        this.publisher.publishEvent(new FansEvent(this, openId));
        String xml = "";
        if (respMsg != null) {
            respMsg.setCreateTime(System.currentTimeMillis() / 1000);
            xml = xStream.toXML(respMsg);
            publisher.publishEvent(new MsgEvent(this, respMsg, this.weixinConfig.getAppId(), MsgDefines.SEND, xml));
        }
        return xml;
    }

    @Override
    public Card batchCard(ReqBatchCatch reqBatchCatch) {
        if (log.isInfoEnabled()) {
            log.info("批量查询卡列表:{}",JSON.toJSONString(reqBatchCatch, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        Card card = this.weixinProxy.batchCard(reqBatchCatch);
        if (log.isInfoEnabled()) {
            log.info("获取的卡券列表信息:{}",JSON.toJSONString(card, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        return card;
    }

}

package com.fenlibao.p2p.weixin.service;

import com.fenlibao.p2p.weixin.config.WeixinConfig;
import com.fenlibao.p2p.weixin.defines.OauthDefines;
import com.fenlibao.p2p.weixin.domain.Fans;
import com.fenlibao.p2p.weixin.message.Message;
import com.fenlibao.p2p.weixin.domain.Qrcode;
import com.fenlibao.p2p.weixin.exception.WeixinException;
import com.fenlibao.p2p.weixin.message.card.CardTypeValue;
import com.fenlibao.p2p.weixin.message.template.TemplateMsg;

import java.io.Serializable;
import java.util.Map;

/**
 * 微信api
 * <p/>
 * 130130118@qq.com
 * AAA111aaa
 * Created by lenovo on 2015/5/24.
 */
public interface WxApi extends Constants {


    /**
     * 获取微信配置信息
     *
     * @return
     */
    WeixinConfig getWeixinConfig();

    /**
     * 根据url生成网页授权获取用户基本信息url
     *
     * @param url
     * @return
     */
    String generateOauth2Url(String url, String state);

    /**
     * 在开发者首次提交验证申请时，微信服务器将发送GET请求到填写的URL上，
     * 并且带上四个参数（signature、timestamp、nonce、echostr），开发者通过对签名
     * （即signature）的效验，来判断此条消息的真实性。此后，每次开发者接收用户消息的时候，
     * 微信也都会带上前面三个参数（signature、timestamp、nonce）访问开发者设置的URL，
     * 开发者依然通过对签名的效验判断此条消息的真实性。效验方式与首次提交验证申请一致。
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
    boolean checkSignature(String signature, String timestamp,
                           String nonce, String echostr);


    /**
     * 微信页面config接入
     *
     * @param url
     * @return
     */
    Map<String, String> signature(final String url);

    /**
     * 微信卡券网页签名
     *
     * @param locationId
     * @param cardId
     * @param cardType
     * @return
     */
    Map<String, String> signature(CardTypeValue cardType, String cardId,String locationId);

    /**
     * access_token是公众号的全局唯一票据，公众号调用各接口时都需使用access_token。
     * 开发者需要进行妥善保存。access_token的存储至少要保留512个字符空间。access_token的
     * 有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效。
     *
     * @return
     */
//    Token getToken();


    /**
     * 开发者可通过OpenID来获取用户基本信息。请使用https协议。
     *
     * @param openid 普通用户的标识，对当前公众号唯一
     * @return
     */
    Fans getFans(String openid) throws WeixinException;

    /**
     * 永久二维码
     * <p/>
     * 为了满足用户渠道推广分析的需要，公众平台提供了生成带参数二维码的接口。
     * 使用该接口可以获得多个带不同场景值的二维码，用户扫描后，公众号可以接收到事件推送。
     * <p/>
     * 用户扫描带场景值二维码时，可能推送以下两种事件：
     * <p/>
     * 如果用户还未关注公众号，则用户可以关注公众号，关注后微信会将带场景值关注事件推送给开发者。
     * 如果用户已经关注公众号，在用户扫描后会自动进入会话，微信也会将带场景值扫描事件推送给开发者。
     * <b>步奏如下：</b>
     * 1.创建二维码ticket
     * 2.通过ticket换取二维码
     *
     * @return
     */
    Qrcode getQrLimitScene(String sceneStr, String scene) throws WeixinException;

    /**
     * 查看Qrcode getQrLimitScene(String sceneStr);说明
     *
     * @param sceneId
     * @return
     */
    Qrcode getQrLimitScene(int sceneId, String scene) throws WeixinException;

    /**
     * 临时二维码
     *
     * @param sceneId
     * @return
     */
    Qrcode selectTempSceneBySceneId(int sceneId, String scene) throws WeixinException;

    /**
     * 1 第一步：用户同意授权，获取code
     * 2 第二步：通过code换取网页授权access_token
     * 3 第三步：刷新access_token（如果需要）
     * 4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
     * 5 附：检验授权凭证（access_token）是否有效
     * 如果code无效，将返回一个带错误编码和错误信息的Fans对象，要求调用者需要进行判断
     *
     * @return
     */
    OauthDefines oauth2(String code, String state);


    /**
     * 处理微信客户端产生的xml数据，处理完成后返回
     *
     * @param message
     * @return
     */
    Serializable process(String message, String host);

    /**
     * 发送模板消息
     *
     * @param templateMsg 模板消息内容
     * @return
     */
    Message send(TemplateMsg templateMsg) throws WeixinException;

}

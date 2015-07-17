package com.fenlibao.p2p.weixin.service;

import com.fenlibao.p2p.weixin.config.WeixinConfig;
import com.fenlibao.p2p.weixin.defines.OauthDefines;
import com.fenlibao.p2p.weixin.domain.Fans;
import com.fenlibao.p2p.weixin.message.Message;
import com.fenlibao.p2p.weixin.domain.Qrcode;
import com.fenlibao.p2p.weixin.exception.WeixinException;
import com.fenlibao.p2p.weixin.message.card.Card;
import com.fenlibao.p2p.weixin.message.card.CardTypeValue;
import com.fenlibao.p2p.weixin.message.card.UserCard;
import com.fenlibao.p2p.weixin.message.card.req.ReqBatchCatch;
import com.fenlibao.p2p.weixin.message.card.req.ReqUserCard;
import com.fenlibao.p2p.weixin.message.template.TemplateMsg;

import java.io.Serializable;
import java.util.List;
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
     * 卡券签名cardSign说明
     * 将 api_ticket（特别说明：api_ticket 相较 appsecret 安全性更高，同时兼容老版本文档中使用的 appsecret 作为签名凭证。）、app_id、location_id、times_tamp、nonce_str、card_id、card_type的value值进行字符串的字典序排序。
     * 将所有参数字符串拼接成一个字符串进行sha1加密，得到cardSign。
     *
     * @param locationId
     * @param cardId
     * @param cardType
     * @return
     */
    Map<String, String> signature(CardTypeValue cardType, String cardId, String locationId);


    /**
     * 里面分两步运行，
     * 1.获取卡券列表信息
     * 2.对卡券列表进行加密签名
     *
     * @param reqBatchCatch
     * @param openid
     * @param code
     * @return
     */
    List<Map<String, Object>> signature(ReqBatchCatch reqBatchCatch, String openid, String code);

    /**
     * 卡券扩展字段cardExt说明
     * <p/>
     * cardExt本身是一个JSON字符串，是商户为该张卡券分配的唯一性信息，包含以下字段：
     * <p/>
     * 字段	        是否必填	 说明
     * code	        否	         指定的卡券code码，只能被领一次。use_custom_code字段为true的卡券必须填写，非自定义code不必填写。
     * openid	    否	         指定领取者的openid，只有该用户能领取。bind_openid字段为true的卡券必须填写，bind_openid字段为false不必填写。
     * timestamp	是	         时间戳，商户生成从1970年1月1日00:00:00至今的秒数,即当前的时间,且最终需要转换为字符串形式;由商户生成后传入。
     * nonce_str	否	         随机字符串，由开发者设置传入，加强签名的安全性。随机字符串，不长于32位。推荐使用大小写字母和数字。
     * signature	是	         签名，商户将接口列表中的参数按照指定方式进行签名,签名方式使用SHA1,具体签名方案参见下文;由商户按照规范签名后传入。
     * 签名说明
     * <p/>
     * 将 api_ticket（特别说明：api_ticket 相较 appsecret 安全性更高，同时兼容老版本文档中使用的 appsecret 作为签名凭证。）、timestamp、card_id、code、openid、nonce_str的value值进行字符串的字典序排序。
     * 将所有参数字符串拼接成一个字符串进行sha1加密，得到signature。
     * signature中的timestamp，nonce字段和card_ext中的timestamp，nonce_str字段必须保持一致。
     * code=jonyqin_1434008071，timestamp=1404896688，card_id=pjZ8Yt1XGILfi-FUsewpnnolGgZk， api_ticket=ojZ8YtyVyr30HheH3CM73y7h4jJE ，nonce_str=jonyqin 则signature=sha1(pjZ8Yt1XGILfi-FUsewpnnolGgZkjonyqin_1434008071ojZ8YtyVyr30HheH3CM73y7h4jJE1404896688jonyqin)=4F76593A4245644FAE4E1BC940F6422A0C3EC03E。
     *
     * @param openid
     * @param code
     * @return
     */
    List<Map<String, Object>> signature(List<String> cardsId, String openid, String code);

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

    /**
     * 获取用户已领取卡券接口
     *
     * @param params
     * @return
     */
    UserCard getUserCardList(ReqUserCard params);


    /**
     * 批量查询卡列表
     * 参数名	       必填	类型	       示例值	                          描述
     * offset	        是	     int	     0	                             查询卡列表的起始偏移量，从0开始，即offset: 5是指从从列表里的第六个开始读取。
     * count	        是	     int	     10	                             需要查询的卡片的数量（数量最大50）。
     * status_list	    否	     int	     CARD_STATUS_VERIFY_OK	         支持开发者拉出指定状态的卡券列表，例：仅拉出通过审核的卡券。
     *
     * @param reqBatchCatch
     * @return
     */
    Card batchCard(ReqBatchCatch reqBatchCatch);
}

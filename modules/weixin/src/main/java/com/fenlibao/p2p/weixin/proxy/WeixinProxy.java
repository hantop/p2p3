package com.fenlibao.p2p.weixin.proxy;


import com.alibaba.fastjson.JSONObject;
import com.fenlibao.p2p.weixin.annotation.Thing;
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
import com.fenlibao.p2p.weixin.message.card.req.ReqUserCard;
import com.fenlibao.p2p.weixin.message.req.ReqTicket;
import com.fenlibao.p2p.weixin.message.req.White;
import com.fenlibao.p2p.weixin.service.Constants;
import com.fenlibao.p2p.weixin.variable.WeiXinThing;

import java.io.IOException;

/**
 * Created by Administrator on 2015/6/10.
 */

public interface WeixinProxy extends Constants {

    /**
     * tokey令牌
     *
     * @return
     */
    String getTokenKey();

    /**
     * 获取token，内部自己维护token信息
     *
     * @return
     */
    @Thing(WeiXinThing.HTTP_TOKEN)
    Token httpToken();

    /**
     * 根据url获取用户信息
     *
     * @param url
     * @return
     */
    @Thing(WeiXinThing.HTTP_FANS)
    Fans httpFans(String url);

    /**
     * 换二维码的ticket
     *
     * @param reqTicket
     * @return
     */
    @Thing(WeiXinThing.HTTP_TICKET)
    Ticket httpTicket(ReqTicket reqTicket);


    /**
     * jsapi_ticket
     *
     * @return
     */
    @Thing(WeiXinThing.HTTP_TICKET)
    Ticket httpTicket(TicketType ticketType);

    /**
     * 网页授权获取用户信息
     *
     * @param code
     * @return
     */
    @Thing(WeiXinThing.HTTP_TOKEN)
    Token httpToken(String code) throws WeixinException;

    /**
     * 获取二维码
     *
     * @param reqTicket
     * @return
     * @throws WeixinException
     */
    @Thing(WeiXinThing.HTTP_QRCODE)
    Qrcode httpQrcode(ReqTicket reqTicket, String scene);


    /**
     * 发送模板消息
     *
     * @param templateMsg
     * @return
     */
    @Thing(WeiXinThing.HTTP_TEMPLATE_MSG)
    Message httpTemplateMsg(String templateMsg);


    /**
     * POST数据示例
     * {
     * "poi_id":"271262077"
     * }
     * 获取门店信息
     *
     * @param poiId
     * @return
     */
    @Thing(WeiXinThing.HTTP_POI)
    Poi httpPoi(String poiId);

    /**
     * 设置测试白名单
     * 1.同时支持“openid”、“username”两种字段设置白名单，总数上限为10个。
     * 2.设置测试白名单接口为全量设置，即测试名单发生变化时需调用该接口重新传入所有测试人员的ID.
     * 参数说明
     * <p/>
     * 参数	是否必须	说明
     * access_token	是	调用接口凭证
     * POST数据	是	Json数据
     * {
     * "openid": [
     * "o1Pj9jmZvwSyyyyyyBa4aULW2mA",
     * "o1Pj9jmZvxxxxxxxxxULW2mA"
     * ],
     * "username": [
     * "afdvvf",
     * "abcd"
     * ]
     * }
     * 参数名	必填	类型	示例值	描述
     * openid	否	string(20)	o1Pj9jmZvwSyyyyyyBa4aULW2mA	测试的openid列表。
     * username	否	string（32）	eddy	测试的微信号列表。
     * {
     * "errcode":0,
     * "errmsg":"ok"
     * }
     * errcode	错误码，0为正常。
     * errmsg	错误信息。
     *
     * @param white
     * @return
     */
    WxMsg testwhitelist(White white);


    /**
     * 核销卡券
     * 线下核销
     * 核销Code接口
     * 消耗code接口是核销卡券的唯一接口，仅支持核销有效期内的卡券，否则会返回错误码invalid time。
     * 自定义Code码（use_custom_code为true）的优惠券，在code被核销时，必须调用此接口。用于将用户客户端的code状态变更。自定义code的卡券调用接口时， post数据中需包含card_id，非自定义code不需上报。
     *
     * @param message
     * @return
     */
//    Message consume(Message message);

    /**
     * code解码接口支持两种场景： 1.商家获取choos_card_info后，将card_id和encrypt_code字段通过解码接口，获取真实code。 2.卡券内跳转外链的签名中会对code进行加密处理，通过调用解码接口获取真实code。
     *
     * @param message
     * @return
     */
//    Message encryptCode(Message message);


    /**
     * 获取用户已领取卡券接口
     *
     * @param params
     * @return
     */
    UserCard getUserCardList(ReqUserCard params);

    /**
     * 查看卡券详情
     * 调用该接口可查询卡券字段详情及卡券所处状态。建议开发者调用卡券更新信息接口后调用该接口验证是否更新成功。
     *
     * @param params
     * @return
     */
    Card getCard(JSONObject params) throws IOException;

    /**
     * 根据OpenID列表群发【订阅号不可用，服务号认证后可用】
     * @param params
     * @return
     */
    byte[] messageMassSend(JSONObject params);
}

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
import com.fenlibao.p2p.weixin.message.card.req.ReqBatchCatch;
import com.fenlibao.p2p.weixin.message.card.req.ReqUserCard;
import com.fenlibao.p2p.weixin.message.req.ReqTicket;
import com.fenlibao.p2p.weixin.message.req.White;
import com.fenlibao.p2p.weixin.message.template.TemplateMsg;
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
    Message httpTemplateMsg(TemplateMsg templateMsg);


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

package com.fenlibao.p2p.weixin.proxy;


import com.fenlibao.p2p.weixin.annotation.Thing;
import com.fenlibao.p2p.weixin.domain.*;
import com.fenlibao.p2p.weixin.exception.WeixinException;
import com.fenlibao.p2p.weixin.message.Message;
import com.fenlibao.p2p.weixin.message.req.ReqTicket;
import com.fenlibao.p2p.weixin.service.Constants;
import com.fenlibao.p2p.weixin.variable.WeiXinThing;

/**
 * Created by Administrator on 2015/6/10.
 */

public interface WeixinProxy extends Constants {

    /**
     * tokey令牌
     * @return
     */
    String getTokenKey();

    /**
     * 获取token，内部自己维护token信息
     * @return
     */
    @Thing(WeiXinThing.HTTP_TOKEN)
    Token httpToken();

    /**
     * 根据url获取用户信息
     * @param url
     * @return
     */
    @Thing(WeiXinThing.HTTP_FANS)
    Fans httpFans(String url);

    /**
     * 换二维码的ticket
     * @param reqTicket
     * @return
     */
    @Thing(WeiXinThing.HTTP_TICKET)
    Ticket httpTicket(ReqTicket reqTicket);


    /**
     * jsapi_ticket
     * @return
     */
    @Thing(WeiXinThing.HTTP_TICKET)
    Ticket httpTicket();

    /**
     * 网页授权获取用户信息
     * @param code
     * @return
     */
    @Thing(WeiXinThing.HTTP_TOKEN)
    Token httpToken(String code) throws WeixinException;

    /**
     * 获取二维码
     * @param reqTicket
     * @return
     * @throws WeixinException
     */
    @Thing(WeiXinThing.HTTP_QRCODE)
    Qrcode httpQrcode(ReqTicket reqTicket, String scene);

    /**
     * 发送模板消息
     * @param templateMsg
     * @return
     */
    @Thing(WeiXinThing.HTTP_TEMPLATE_MSG)
    Message httpTemplateMsg(String templateMsg);
}

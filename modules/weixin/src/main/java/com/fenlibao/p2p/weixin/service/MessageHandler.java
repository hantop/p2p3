package com.fenlibao.p2p.weixin.service;

import com.fenlibao.p2p.weixin.domain.Fans;
import com.fenlibao.p2p.weixin.message.Message;

/**
 * 消息处理类，要求之类实现该接口实现直接的业务
 * Created by Administrator on 2015/6/18.
 */
public interface MessageHandler {

    /**
     * 二维码扫描事件
     * @param message
     * @return
     */
    Message scanEvent(Message message, WxApi wxApi, String host);

    /**
     * 网页授权
     * @param userInfo
     * @param state
     * @return
     */
    Message oauth2Event(Fans userInfo, String state);
}

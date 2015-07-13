package com.fenlibao.p2p.weixin.event;

import com.fenlibao.p2p.weixin.defines.MsgDefines;
import com.fenlibao.p2p.weixin.message.Message;
import org.springframework.context.ApplicationEvent;

/**
 * Created by Administrator on 2015/7/9.
 */
public class MsgEvent extends ApplicationEvent {

    private Message message;
    private String appId;
    private MsgDefines type;
    private String content;

    public MsgEvent(Object source, Message message, String appId, MsgDefines type, String content) {
        super(source);
        this.message = message;
        this.appId = appId;
        this.type = type;
        this.content = content;
    }

    public Message getMessage() {
        return message;
    }

    public String getAppId() {
        return appId;
    }

    public MsgDefines getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}

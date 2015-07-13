package com.fenlibao.p2p.weixin.event;

import com.fenlibao.p2p.weixin.message.Message;
import org.springframework.context.ApplicationEvent;

/**
 * Created by Administrator on 2015/7/13.
 */
public class PoiCheckEvent extends ApplicationEvent {

    private Message message;

    public PoiCheckEvent(Object source, Message message) {
        super(source);
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }
}

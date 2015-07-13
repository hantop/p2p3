package com.fenlibao.p2p.weixin.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by Administrator on 2015/7/10.
 */
public class FansEvent extends ApplicationEvent {

    private String openid;

    public FansEvent(Object source, String openid) {
        super(source);
        this.openid = openid;
    }

    public String getOpenid() {
        return openid;
    }
}

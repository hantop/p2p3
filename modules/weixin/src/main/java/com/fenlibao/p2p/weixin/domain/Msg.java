package com.fenlibao.p2p.weixin.domain;

import com.fenlibao.p2p.weixin.message.Message;

public class Msg extends Message {

    private String id;

    private String appId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
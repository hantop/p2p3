package com.fenlibao.p2p.weixin.domain;

import com.fenlibao.p2p.weixin.defines.MsgType;

public class Media extends com.fenlibao.p2p.weixin.message.Media{

    private String id;

    private String messageId;

    private MsgType type;//媒体类型

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public MsgType getType() {
        return type;
    }

    public void setType(MsgType type) {
        this.type = type;
    }
}
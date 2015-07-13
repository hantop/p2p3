package com.fenlibao.p2p.weixin.domain;

import com.fenlibao.p2p.weixin.defines.MsgDefines;

import java.sql.Timestamp;

public class Msg {

    private Long id;

    private String appId;

    private String toUserName;

    private String fromUserName;

    private Long createTime;

    private String msgId;

    private String msgType;

    private String event;

    private MsgDefines type;

    private MsgDefines format;

    private String content;

    public Msg(String appId, String toUserName, String fromUserName, Long createTime, String msgId, String msgType, String event, MsgDefines type, MsgDefines format, String content) {
        this.appId = appId;
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = createTime;
        this.msgId = msgId;
        this.msgType = msgType;
        this.event = event;
        this.type = type;
        this.format = format;
        this.content = content;
    }

    public Msg() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public MsgDefines getType() {
        return type;
    }

    public void setType(MsgDefines type) {
        this.type = type;
    }

    public MsgDefines getFormat() {
        return format;
    }

    public void setFormat(MsgDefines format) {
        this.format = format;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
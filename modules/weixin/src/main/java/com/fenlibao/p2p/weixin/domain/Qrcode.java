package com.fenlibao.p2p.weixin.domain;

import com.fenlibao.p2p.weixin.message.WxMsg;

public class Qrcode extends WxMsg{
    private String id;

    private String sceneName;

    private String actionName;

    private String sceneValue;

    private String sceneType;

    private String ticketId;

    private Long createTime;

    private String suffix;

    private String name;

    private Boolean enable;

    private Long logId;

    private byte[] bytes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName == null ? null : sceneName.trim();
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName == null ? null : actionName.trim();
    }

    public String getSceneValue() {
        return sceneValue;
    }

    public void setSceneValue(String sceneValue) {
        this.sceneValue = sceneValue == null ? null : sceneValue.trim();
    }

    public String getSceneType() {
        return sceneType;
    }

    public void setSceneType(String sceneType) {
        this.sceneType = sceneType == null ? null : sceneType.trim();
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId == null ? null : ticketId.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix == null ? null : suffix.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
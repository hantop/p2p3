package com.fenlibao.p2p.weixin.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fenlibao.p2p.weixin.message.WxMsg;
import com.fenlibao.p2p.weixin.service.Constants;

public class Ticket extends WxMsg {

    private Long logId;

    private Long createTime;

    @JsonProperty("ticket")
    @JSONField(name = "ticket")
    private String ticket;

    @JsonProperty("expire_seconds")
    @JSONField(name = "expire_seconds")
    private Long expireSeconds;

    @JsonProperty("expires_in")
    @JSONField(name = "expires_in")
    private Long expiresIn;

    @JsonProperty("url")
    @JSONField(name = "url")
    private String url;

    private Constants.TicketType type;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket == null ? null : ticket.trim();
    }

    public Long getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(Long expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Constants.TicketType getType() {
        return type;
    }

    public void setType(Constants.TicketType type) {
        this.type = type;
    }
}
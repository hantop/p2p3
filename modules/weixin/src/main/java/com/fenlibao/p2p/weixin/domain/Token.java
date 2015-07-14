package com.fenlibao.p2p.weixin.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fenlibao.p2p.weixin.message.WxMsg;
import com.fenlibao.p2p.weixin.service.Constants;

public class Token  extends WxMsg {

    @JsonProperty("expires_in")
    @JSONField(name = "expires_in")
    private Long expiresIn;

    @JsonProperty("refresh_token")
    @JSONField(name = "refresh_token")
    private String refreshToken;

    @JsonProperty("scope")
    @JSONField(name = "scope")
    private String scope;

    @JsonProperty("unionid")
    @JSONField(name = "unionid")
    private String unionid;

    private Constants.TokeyType type;

    private Long logId;


    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken == null ? null : refreshToken.trim();
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public Constants.TokeyType getType() {
        return type;
    }

    public void setType(Constants.TokeyType type) {
        this.type = type;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }
}
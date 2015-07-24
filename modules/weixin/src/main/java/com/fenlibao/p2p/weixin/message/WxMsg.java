package com.fenlibao.p2p.weixin.message;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fenlibao.p2p.weixin.defines.CodeMsg;

import java.io.Serializable;

/**
 * Created by lenovo on 2015/5/24.
 */
public class WxMsg implements Serializable {

    private String id;

    //回话token
    @JsonProperty("access_token")
    @JSONField(name = "access_token")
    private String accessToken;

    //错误编码
    @JsonProperty("errcode")
    @JSONField(name = "errcode")
    private int errcode;

    //错误消息
    @JsonProperty("errmsg")
    @JSONField(name = "errmsg")
    private String errmsg;

    //用户标识
    @JsonProperty("openid")
    @JSONField(name = "openid")
    private String openid;

    @JsonProperty("msg")
    @JSONField(name = "msg")
    private String msg;

    private Long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        CodeMsg codeMsg = CodeMsg.parse(errcode);
        this.msg = codeMsg.getErrmsg();
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCreateTime() {
        if(this.createTime == null) {
            this.createTime = System.currentTimeMillis();
        }
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}

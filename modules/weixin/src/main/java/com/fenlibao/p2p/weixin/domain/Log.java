package com.fenlibao.p2p.weixin.domain;

import java.util.Date;

public class Log {
    private Long id;

    private String appId;

    private String target;

    private String invoke;

    private String argus;

    private String code;

    private String errMsg;

    private String thing;

    private Date createTime;

    public Log(String appId, String target, String invoke, String argus, String code, String errMsg, String thing) {
        this.appId = appId;
        this.target = target;
        this.invoke = invoke;
        this.argus = argus;
        this.code = code;
        this.errMsg = errMsg;
        this.thing = thing;
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
        this.appId = appId == null ? null : appId.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getInvoke() {
        return invoke;
    }

    public void setInvoke(String invoke) {
        this.invoke = invoke == null ? null : invoke.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg == null ? null : errMsg.trim();
    }

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing == null ? null : thing.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getArgus() {
        return argus;
    }

    public void setArgus(String argus) {
        this.argus = argus == null ? null : argus.trim();
    }
}
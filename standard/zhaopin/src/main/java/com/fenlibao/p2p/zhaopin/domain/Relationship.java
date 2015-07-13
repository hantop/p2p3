package com.fenlibao.p2p.zhaopin.domain;

import com.fenlibao.p2p.weixin.domain.Fans;

import java.util.Date;

public class Relationship {
    private Long id;

    private String fromUserOpenid;

    private String fromUserName;

    private Fans fromUser;

    private String fromUserNamePhone;

    private String toUserOpenid;

    private String toUserName;

    private Fans toUser;

    private String toUserNamePhone;

    private Boolean tryStatus;

    private Boolean positiveStatus;

    private Double trialPrice;

    private Double positivePrice;

    private Date browseTime;

    private String fileName;

    private String suffix;

    private byte[] resume;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromUserOpenid() {
        return fromUserOpenid;
    }

    public void setFromUserOpenid(String fromUserOpenid) {
        this.fromUserOpenid = fromUserOpenid == null ? null : fromUserOpenid.trim();
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName == null ? null : fromUserName.trim();
    }

    public String getFromUserNamePhone() {
        return fromUserNamePhone;
    }

    public void setFromUserNamePhone(String fromUserNamePhone) {
        this.fromUserNamePhone = fromUserNamePhone == null ? null : fromUserNamePhone.trim();
    }

    public String getToUserOpenid() {
        return toUserOpenid;
    }

    public void setToUserOpenid(String toUserOpenid) {
        this.toUserOpenid = toUserOpenid == null ? null : toUserOpenid.trim();
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName == null ? null : toUserName.trim();
    }

    public String getToUserNamePhone() {
        return toUserNamePhone;
    }

    public void setToUserNamePhone(String toUserNamePhone) {
        this.toUserNamePhone = toUserNamePhone == null ? null : toUserNamePhone.trim();
    }

    public Boolean getTryStatus() {
        return tryStatus;
    }

    public void setTryStatus(Boolean tryStatus) {
        this.tryStatus = tryStatus;
    }

    public Boolean getPositiveStatus() {
        return positiveStatus;
    }

    public void setPositiveStatus(Boolean positiveStatus) {
        this.positiveStatus = positiveStatus;
    }

    public Double getTrialPrice() {
        return trialPrice;
    }

    public void setTrialPrice(Double trialPrice) {
        this.trialPrice = trialPrice;
    }

    public Double getPositivePrice() {
        return positivePrice;
    }

    public void setPositivePrice(Double positivePrice) {
        this.positivePrice = positivePrice;
    }

    public Date getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix == null ? null : suffix.trim();
    }

    public byte[] getResume() {
        return resume;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public Fans getFromUser() {
        return fromUser;
    }

    public void setFromUser(Fans fromUser) {
        this.fromUser = fromUser;
    }

    public Fans getToUser() {
        return toUser;
    }

    public void setToUser(Fans toUser) {
        this.toUser = toUser;
    }

}
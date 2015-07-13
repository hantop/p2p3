package com.fenlibao.p2p.zhaopin.domain;

import java.util.Date;

public class Rule {
    private Long id;

    private String operator;

    private String name;

    private Double recommendSuccess;

    private Double trialSuccess;

    private Double positiveSuccess;

    private Boolean status;

    private Date createTime;

    private Boolean del;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getRecommendSuccess() {
        return recommendSuccess;
    }

    public void setRecommendSuccess(Double recommendSuccess) {
        this.recommendSuccess = recommendSuccess;
    }

    public Double getTrialSuccess() {
        return trialSuccess;
    }

    public void setTrialSuccess(Double trialSuccess) {
        this.trialSuccess = trialSuccess;
    }

    public Double getPositiveSuccess() {
        return positiveSuccess;
    }

    public void setPositiveSuccess(Double positiveSuccess) {
        this.positiveSuccess = positiveSuccess;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }
}
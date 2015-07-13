package com.fenlibao.p2p.zhaopin.domain;

import java.sql.Timestamp;

public class RelationshipHistory {
    private Long id;

    private Long relationshipId;

    private Relationship relationship;

    private Boolean provide;

    private Timestamp provideTime;

    private String operator;

    private Double providePrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public Boolean getProvide() {
        return provide;
    }

    public void setProvide(Boolean provide) {
        this.provide = provide;
    }

    public Timestamp getProvideTime() {
        return provideTime;
    }

    public void setProvideTime(Timestamp provideTime) {
        this.provideTime = provideTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Double getProvidePrice() {
        return providePrice;
    }

    public void setProvidePrice(Double providePrice) {
        this.providePrice = providePrice;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }
}
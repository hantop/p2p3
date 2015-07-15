package com.fenlibao.p2p.weixin.message.card;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrator on 2015/7/15.
 */
public class CardDetail {

    @JsonProperty("base_info")
    @JSONField(name = "base_info")
    private BaseInfo baseInfo;

    /**
     * 团购券专用字段，团购详情。
     */
    @JsonProperty("deal_detail")
    @JSONField(name = "deal_detail")
    private String dealDetail;

    /**
     * 代金券专用，表示起用金额。（单位为分）
     */
    @JsonProperty("least_cost")
    @JSONField(name = "least_cost")
    private Integer leastCost;

    /**
     * 代金券专用，表示减免金额。（单位为分）
     */
    @JsonProperty("reduce_cost")
    @JSONField(name = "reduce_cost")
    private Integer reduceCost;

    /**
     * 折扣券专用，表示打折额度（百分比）。填30就是七折。
     */
    @JsonProperty("discount")
    @JSONField(name = "discount")
    private Integer discount;

    /**
     * 礼品券专用，填写礼品的名称。
     */
    @JsonProperty("gift")
    @JSONField(name = "gift")
    private String gift;

    /**
     * 优惠券专用，填写优惠详情。
     */
    @JsonProperty("default_detail")
    @JSONField(name = "default_detail")
    private String defaultDetail;

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public String getDealDetail() {
        return dealDetail;
    }

    public void setDealDetail(String dealDetail) {
        this.dealDetail = dealDetail;
    }

    public Integer getLeastCost() {
        return leastCost;
    }

    public void setLeastCost(Integer leastCost) {
        this.leastCost = leastCost;
    }

    public Integer getReduceCost() {
        return reduceCost;
    }

    public void setReduceCost(Integer reduceCost) {
        this.reduceCost = reduceCost;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getDefaultDetail() {
        return defaultDetail;
    }

    public void setDefaultDetail(String defaultDetail) {
        this.defaultDetail = defaultDetail;
    }
}

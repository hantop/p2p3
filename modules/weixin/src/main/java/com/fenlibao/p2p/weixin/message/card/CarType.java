package com.fenlibao.p2p.weixin.message.card;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/15.
 */
public class CarType implements Serializable {

    /**
     * 卡券类型。
     * 团购券：GROUPON;
     * 折扣券：DISCOUNT;
     * 礼品券：GIFT;
     * 代金券：CASH;
     * 通用券：GENERAL_COUPON;
     * 会员卡：MEMBER_CARD;
     * 景点门票：SCENIC_TICKET；
     * 电影票：MOVIE_TICKET；
     * 飞机票：BOARDING_PASS；
     * 会议门票：MEETING_TICKET；
     * 汽车票：BUS_TICKET;
     */
    @JsonProperty("card_type")
    @JSONField(name = "card_type")
    private CardTypeValue cardTypeValue;

    /**
     * 代金券
     */
    @JsonProperty("cash")
    @JSONField(name = "cash")
    private CardDetail cash;


    /**
     * 折扣券
     */
    @JsonProperty("discount")
    @JSONField(name = "discount")
    private CardDetail discount;

    /**
     * 礼品券
     */
    @JsonProperty("gift")
    @JSONField(name = "gift")
    private CardDetail gift;

    /**
     * 团购券
     */
    @JsonProperty("groupon")
    @JSONField(name = "groupon")
    private CardDetail groupon;

    /**
     * 优惠券
     */
    @JsonProperty("general_coupon")
    @JSONField(name = "general_coupon")
    private CardDetail generalCoupon;

    public CardTypeValue getCardTypeValue() {
        return cardTypeValue;
    }

    public void setCardTypeValue(CardTypeValue cardTypeValue) {
        this.cardTypeValue = cardTypeValue;
    }

    public CardDetail getCash() {
        return cash;
    }

    public void setCash(CardDetail cash) {
        this.cash = cash;
    }

    public CardDetail getDiscount() {
        return discount;
    }

    public void setDiscount(CardDetail discount) {
        this.discount = discount;
    }

    public CardDetail getGift() {
        return gift;
    }

    public void setGift(CardDetail gift) {
        this.gift = gift;
    }

    public CardDetail getGroupon() {
        return groupon;
    }

    public void setGroupon(CardDetail groupon) {
        this.groupon = groupon;
    }

    public CardDetail getGeneralCoupon() {
        return generalCoupon;
    }

    public void setGeneralCoupon(CardDetail generalCoupon) {
        this.generalCoupon = generalCoupon;
    }
}

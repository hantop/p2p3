package com.fenlibao.p2p.weixin.message.card;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fenlibao.p2p.weixin.message.WxMsg;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/15.
 */
public class Card extends WxMsg implements Serializable {

    @JsonProperty("card")
    @JSONField(name = "card")
    private CarType carType;

    @JsonProperty("card_id_list")
    @JSONField(name = "card_id_list")
    private String[] cardIdList;//卡券ID列表。

    @JsonProperty("total_num")
    @JSONField(name = "total_num")
    private Integer total_num;//total_num

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String[] getCardIdList() {
        return cardIdList;
    }

    public void setCardIdList(String[] cardIdList) {
        this.cardIdList = cardIdList;
    }

    public Integer getTotal_num() {
        return total_num;
    }

    public void setTotal_num(Integer total_num) {
        this.total_num = total_num;
    }
}

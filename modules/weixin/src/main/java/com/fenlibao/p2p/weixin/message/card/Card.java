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

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}

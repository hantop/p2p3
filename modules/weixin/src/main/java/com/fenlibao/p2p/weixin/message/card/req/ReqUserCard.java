package com.fenlibao.p2p.weixin.message.card.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/17.
 */
public class ReqUserCard implements Serializable {


    @NotNull(message = "待查询的openid不能为空")
    @JsonProperty("openid")
    @JSONField(name = "openid")
    private String openid;

    @JsonProperty("card_id")
    @JSONField(name = "card_id")
    private String cardId;

    public ReqUserCard() {
    }

    public ReqUserCard(String openid, String cardId) {
        this.openid = openid;
        this.cardId = cardId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}

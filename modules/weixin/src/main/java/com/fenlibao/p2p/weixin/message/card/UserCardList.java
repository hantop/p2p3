package com.fenlibao.p2p.weixin.message.card;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrator on 2015/7/17.
 */
public class UserCardList {

    @JsonProperty("code")
    @JSONField(name = "code")
    private String code;

    @JsonProperty("card_id")
    @JSONField(name = "card_id")
    private String cardId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}

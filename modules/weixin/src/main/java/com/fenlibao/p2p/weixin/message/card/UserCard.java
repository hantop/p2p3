package com.fenlibao.p2p.weixin.message.card;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fenlibao.p2p.weixin.message.WxMsg;

import java.io.Serializable;
import java.util.List;

/**
 * 用户卡券信息
 * Created by Administrator on 2015/7/17.
 */
public class UserCard extends WxMsg implements Serializable {

    @JsonProperty("card_list")
    @JSONField(name = "card_list")
    private List<UserCardList> userCardLists;

    public List<UserCardList> getUserCardLists() {
        return userCardLists;
    }

    public void setUserCardLists(List<UserCardList> userCardLists) {
        this.userCardLists = userCardLists;
    }


}

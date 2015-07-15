package com.fenlibao.p2p.weixin.message.card;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/15.
 */
public enum CardTypeValue implements Serializable {

    GROUPON("团购券"),
    DISCOUNT("折扣券"),
    GIFT("礼品券"),
    CASH("代金券"),
    GENERAL_COUPON("通用券"),
    MEMBER_CARD("会员卡"),
    SCENIC_TICKET("景点门票"),
    MOVIE_TICKET("电影票"),
    BOARDING_PASS("飞机票"),
    MEETING_TICKET("会议门票"),
    BUS_TICKET("汽车票");

    private String name;

    CardTypeValue(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

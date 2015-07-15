package com.fenlibao.p2p.weixin.message.card;

/**
 * 使用时间的类型
 * Created by Administrator on 2015/7/15.
 */
public enum Type {

    DATE_TYPE_FIX_TIME_RANGE("表示固定日期区间"),
    DATE_TYPE_FIX_TERM("表示固定时长（自领取后按天算）"),
    DATE_TYPE_PERMANENT("表示永久有效（会员卡类型专用）");

    private String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

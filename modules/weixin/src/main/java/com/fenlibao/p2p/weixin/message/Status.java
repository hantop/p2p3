package com.fenlibao.p2p.weixin.message;

/**
 * Created by Administrator on 2015/7/15.
 */
public enum Status {

    CARD_STATUS_DISPATCH("已投放"),
    CARD_STATUS_NOT_VERIFY("待审核"),
    CARD_STATUS_VERIFY_FALL("审核失败"),
    CARD_STATUS_VERIFY_OK("通过审核"),
    CARD_STATUS_USER_DELETE("卡券被用户删除"),
    CARD_STATUS_USER_DISPATCH("在公众平台投放过的卡券");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

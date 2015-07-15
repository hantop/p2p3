package com.fenlibao.p2p.weixin.message.card;

/**
 * Code码展示类型
 * Created by Administrator on 2015/7/15.
 */

public enum CodeType {

    CODE_TYPE_TEXT("文本"),
    CODE_TYPE_BARCODE("一维码 "),
    CODE_TYPE_QRCODE("二维码"),
    CODE_TYPE_ONLY_QRCODE("二维码无code显示"),
    CODE_TYPE_ONLY_BARCODE("一维码无code显示");

    private String name;

    CodeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

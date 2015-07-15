package com.fenlibao.p2p.weixin.message.card;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 商品信息
 * Created by Administrator on 2015/7/15.
 */
public class Sku {

    private String id;

    /**
     * 卡券库存的数量，不支持填写0。（上限为100000000）
     */
    @JsonProperty("quantity")
    @JSONField(name = "quantity")
    private Long quantity;

    /**
     *
     */
    @JsonProperty("total_quantity")
    @JSONField(name = "total_quantity")
    private Long totalQuantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}

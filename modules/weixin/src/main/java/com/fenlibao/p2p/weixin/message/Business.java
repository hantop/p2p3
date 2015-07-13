package com.fenlibao.p2p.weixin.message;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 门店信息
 * Created by Administrator on 2015/7/13.
 */
public class Business implements Serializable {

    @JsonProperty("base_info")
    @JSONField(name = "base_info")
    private com.fenlibao.p2p.weixin.domain.Business baseInfo;

    public com.fenlibao.p2p.weixin.domain.Business getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(com.fenlibao.p2p.weixin.domain.Business baseInfo) {
        this.baseInfo = baseInfo;
    }
}

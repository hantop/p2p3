package com.fenlibao.p2p.weixin.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrator on 2015/7/13.
 */
public class Poi extends WxApiMsg {

    @JsonProperty("business")
    @JSONField(name = "business")
    private Business business;

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public static void main(String[] args) {
        Poi poi = new Poi();
        Business business = new Business();
        com.fenlibao.p2p.weixin.domain.Business baseInfo = new com.fenlibao.p2p.weixin.domain.Business();
        poi.setBusiness(business);
        business.setBaseInfo(baseInfo);
        baseInfo.setAddress("address");
        baseInfo.setAvailableState(1);
        baseInfo.setAvailableState(1);
        System.out.println(JSON.toJSON(poi));
    }
}

package com.fenlibao.p2p.weixin.message.card.req;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fenlibao.p2p.weixin.message.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/7/17.
 */
public class ReqBatchCatch implements Serializable{

    @NotNull(message = "查询卡列表的起始偏移量不能为空")
    @JsonProperty("offset")
    @JSONField(name = "offset")
    private Integer offset;//查询卡列表的起始偏移量，从0开始，即offset: 5是指从从列表里的第六个开始读取。
    @NotNull(message = "需要查询的卡片的数量不能为空")
    @JsonProperty("count")
    @JSONField(name = "count")
    private Integer count;//需要查询的卡片的数量（数量最大50）。
    @JsonProperty("status_list")
    @JSONField(name = "status_list")
    private List<Status> statusList;//支持开发者拉出指定状态的卡券列表，例：仅拉出通过审核的卡券。

    public ReqBatchCatch(Integer offset, Integer count) {
        this.offset = offset;
        this.count = count;
    }

    public ReqBatchCatch(Integer offset, Integer count, List<Status> statusList) {
        this.offset = offset;
        this.count = count;
        this.statusList = statusList;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

package com.fenlibao.p2p.weixin.message.card;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 使用日期，有效期的信息。
 * Created by Administrator on 2015/7/15.
 */
public class DateInfo {


    /**使用时间的类型，旧文档采用的1和2依然生效。*/
    @JsonProperty("type")
    @JSONField(name = "type")
    private Type type;

    /**
     * type为DATE_TYPE_FIX_TIME_RANGE时专用，表示起用时间。从1970年1月1日00:00:00至起用时间的秒数，最终需转换为字符串形态传入，下同。（单位为秒）
     */
    @JsonProperty("begin_timestamp")
    @JSONField(name = "begin_timestamp")
    private Long beginTimestamp;

    /**
     * type为DATE_TYPE_FIX_TIME_RANGE时专用，表示结束时间，建议设置为截止日期的23:59:59过期。（东八区时间，单位为秒）
     */
    @JsonProperty("end_timestamp")
    @JSONField(name = "end_timestamp")
    private Long endTimestamp;

    /**
     * type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天内有效，领取后当天有效填写0。（单位为天）
     */
    @JsonProperty("fixed_term")
    @JSONField(name = "fixed_term")
    private Integer fixedTerm;

    /**
     * type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天开始生效。（单位为天）
     */
    @JsonProperty("fixed_begin_term")
    @JSONField(name = "fixed_begin_term")
    private Integer fixedBeginTerm;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getBeginTimestamp() {
        return beginTimestamp;
    }

    public void setBeginTimestamp(Long beginTimestamp) {
        this.beginTimestamp = beginTimestamp;
    }

    public Long getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public Integer getFixedTerm() {
        return fixedTerm;
    }

    public void setFixedTerm(Integer fixedTerm) {
        this.fixedTerm = fixedTerm;
    }

    public Integer getFixedBeginTerm() {
        return fixedBeginTerm;
    }

    public void setFixedBeginTerm(Integer fixedBeginTerm) {
        this.fixedBeginTerm = fixedBeginTerm;
    }
}

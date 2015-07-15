package com.fenlibao.p2p.weixin.message.card;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fenlibao.p2p.weixin.message.Status;

/**
 * Created by Administrator on 2015/7/15.
 */
public class BaseInfo {

    /**审核状态*/
    @JsonProperty("status")
    @JSONField(name = "status")
    private Status status;

    @JsonProperty("id")
    @JSONField(name = "id")
    private String id;

    /**
     * 卡券的商户logo，建议像素为300*300。
     */
    @JsonProperty("logo_url")
    @JSONField(name = "logo_url")
    private String logoUrl;


    /**
     * 应用的appid
     */
    @JsonProperty("appid")
    @JSONField(name = "appid")
    private String appid;


    /**
     * Code码展示类型
     */
    @JsonProperty("code_type")
    @JSONField(name = "code_type")
    private CodeType codeType;


    /**
     * 商户名字（填写直接提供服务的商户名，第三方商户名填写在source字段）
     */
    @JsonProperty("brand_name")
    @JSONField(name = "brand_name")
    private String brandName;

    /**
     * 卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)。
     */
    @JsonProperty("title")
    @JSONField(name = "title")
    private String title;

    /**
     * 券名，字数上限为18个汉字。
     */
    @JsonProperty("sub_title")
    @JSONField(name = "sub_title")
    private String subTitle;

    /**
     * 使用日期，有效期的信息。
     */
    @JsonProperty("date_info")
    @JSONField(name = "date_info")
    private DateInfo dateInfo;

    /**
     * 卡券的背景颜色。
     */
    @JsonProperty("color")
    @JSONField(name = "color")
    private String color;

    /**
     * 使用提醒，字数上限为16个汉字。
     */
    @JsonProperty("notice")
    @JSONField(name = "notice")
    private String notice;

    /**
     * 客服电话。
     */
    @JsonProperty("service_phone")
    @JSONField(name = "service_phone")
    private String servicePhone;

    /**
     * 卡券使用说明，字数上限为1024个汉字。
     */
    @JsonProperty("description")
    @JSONField(name = "description")
    private String description;

    /**
     *
     */
    @JsonProperty("use_limit")
    @JSONField(name = "use_limit")
    private Integer useLimit;

    /**
     * 每人可领券的数量限制。
     */
    @JsonProperty("get_limit")
    @JSONField(name = "get_limit")
    private Integer getLimit;

    /**
     * 卡券领取页面是否可分享。
     */
    @JsonProperty("can_share")
    @JSONField(name = "can_share")
    private Boolean canShare;

    /**
     * 卡券是否可转赠。
     */
    @JsonProperty("can_give_friend")
    @JSONField(name = "can_give_friend")
    private Boolean canGiveFriend;


    /**
     * 门店位置ID。
     */
    @JsonProperty("location_id_list")
    @JSONField(name = "location_id_list")
    private String[] locationIdList;

    /**
     * 商户自定义入口名称。
     */
    @JsonProperty("custom_url_name")
    @JSONField(name = "custom_url_name")
    private String customUrlName;

    /**
     * 商户自定义入口跳转外链的地址链接,跳转页面内容需与自定义cell名称保持匹配。
     */
    @JsonProperty("custom_url")
    @JSONField(name = "custom_url")
    private String customUrl;

    /**
     * 显示在入口右侧的tips，长度限制在6个汉字内。
     */
    @JsonProperty("custom_url_sub_title")
    @JSONField(name = "custom_url_sub_title")
    private String customUrlSubTitle;

    /**
     * 营销场景的自定义入口。
     */
    @JsonProperty("promotion_url_name")
    @JSONField(name = "promotion_url_name")
    private String promotionUrlName;

    /**
     * 入口跳转外链的地址链接
     */
    @JsonProperty("promotion_url")
    @JSONField(name = "promotion_url")
    private String promotionUrl;

    /**
     * 显示在营销入口右侧的提示语。
     */
    @JsonProperty("promotion_url_sub_title")
    @JSONField(name = "promotion_url_sub_title")
    private String promotionUrlSub_title;

    /**
     * 第三方来源名，例如同程旅游、大众点评。
     */
    @JsonProperty("source")
    @JSONField(name = "source")
    private String source;

    /**
     * 商品信息
     */
    @JsonProperty("sku")
    @JSONField(name = "sku")
    private Sku sku;

    /**
     * 是否自定义Code码。填写true或false，默认为false。通常自有优惠码系统的开发者选择自定义Code码，并在卡券投放时带入Code码，详情见是否自定义Code码。
     */
    @JsonProperty("use_custom_code")
    @JSONField(name = "use_custom_code")
    private Boolean useCustomCode;

    /**
     * 是否指定用户领取，填写true或false。默认为false。通常指定特殊用户群体投放卡券或防止刷券时选择指定用户领取。
     */
    @JsonProperty("bind_openid")
    @JSONField(name = "bind_openid")
    private Boolean bindOpenid;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public CodeType getCodeType() {
        return codeType;
    }

    public void setCodeType(CodeType codeType) {
        this.codeType = codeType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public DateInfo getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(DateInfo dateInfo) {
        this.dateInfo = dateInfo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUseLimit() {
        return useLimit;
    }

    public void setUseLimit(Integer useLimit) {
        this.useLimit = useLimit;
    }

    public Integer getGetLimit() {
        return getLimit;
    }

    public void setGetLimit(Integer getLimit) {
        this.getLimit = getLimit;
    }

    public Boolean getCanShare() {
        return canShare;
    }

    public void setCanShare(Boolean canShare) {
        this.canShare = canShare;
    }

    public Boolean getCanGiveFriend() {
        return canGiveFriend;
    }

    public void setCanGiveFriend(Boolean canGiveFriend) {
        this.canGiveFriend = canGiveFriend;
    }

    public String[] getLocationIdList() {
        return locationIdList;
    }

    public void setLocationIdList(String[] locationIdList) {
        this.locationIdList = locationIdList;
    }

    public String getCustomUrlName() {
        return customUrlName;
    }

    public void setCustomUrlName(String customUrlName) {
        this.customUrlName = customUrlName;
    }

    public String getCustomUrl() {
        return customUrl;
    }

    public void setCustomUrl(String customUrl) {
        this.customUrl = customUrl;
    }

    public String getCustomUrlSubTitle() {
        return customUrlSubTitle;
    }

    public void setCustomUrlSubTitle(String customUrlSubTitle) {
        this.customUrlSubTitle = customUrlSubTitle;
    }

    public String getPromotionUrlName() {
        return promotionUrlName;
    }

    public void setPromotionUrlName(String promotionUrlName) {
        this.promotionUrlName = promotionUrlName;
    }

    public String getPromotionUrl() {
        return promotionUrl;
    }

    public void setPromotionUrl(String promotionUrl) {
        this.promotionUrl = promotionUrl;
    }

    public String getPromotionUrlSub_title() {
        return promotionUrlSub_title;
    }

    public void setPromotionUrlSub_title(String promotionUrlSub_title) {
        this.promotionUrlSub_title = promotionUrlSub_title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    public Boolean getUseCustomCode() {
        return useCustomCode;
    }

    public void setUseCustomCode(Boolean useCustomCode) {
        this.useCustomCode = useCustomCode;
    }

    public Boolean getBindOpenid() {
        return bindOpenid;
    }

    public void setBindOpenid(Boolean bindOpenid) {
        this.bindOpenid = bindOpenid;
    }
}

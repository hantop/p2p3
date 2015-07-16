package com.fenlibao.p2p.weixin.message.req;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fenlibao.p2p.weixin.service.Constants;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/4.
 */
public class ReqTicket implements Serializable {

    @JsonProperty("action_name")
    @JSONField(name = "action_name")
    private Constants.QrcodeType actionName;//二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值

    @JsonProperty("action_info")
    @JSONField(name = "action_info")
    private Map<String, Map<String, Serializable>> actionInfo;//二维码详细信息

    private String cardId;
    private Integer outerId;
    private String code;
    private String openid;
    private Integer expireSeconds;
    private Boolean uniqueCode;


    public ReqTicket(Constants.QrcodeType actionName, Serializable scene) {
        this.actionName = actionName;
        Map<String, Serializable> scenet = new HashMap();
        if (scene instanceof String) {
            scenet.put("scene_str", scene);
            this.actionInfo = new HashMap<>();
            this.actionInfo.put("scene", scenet);
        } else {
            scenet.put("scene_id", scene);
            this.actionInfo = new HashMap<>();
            this.actionInfo.put("scene", scenet);
        }
    }


//    /**
//     * 字符场景
//     * @param actionName
//     * @param sceneStr
//     */
//    public ReqTicket(Constants.QrcodeType actionName, String sceneStr) {
//        this.actionName = actionName;
//        Map<String, Object> scenet = new HashMap();
//        scenet.put("scene_str", sceneStr);
//
//        this.actionInfo = new HashMap<String, Map<String, Object>>();
//        this.actionInfo.put("scene", scenet);
//    }
//
//    /**
//     * 数字场景
//     * @param actionName
//     * @param sceneId
//     */
//    public ReqTicket(Constants.QrcodeType actionName, int sceneId) {
//        this.actionName = actionName;
//        Map<String, Object> scenet = new HashMap();
//        scenet.put("scene_id", sceneId);
//
//        this.actionInfo = new HashMap<String, Map<String, Object>>();
//        this.actionInfo.put("scene", scenet);
//    }

    public ReqTicket(String cardId, Integer outerId, String code, String openid, Integer expireSeconds, Boolean uniqueCode) {
        this.actionName = Constants.QrcodeType.QR_CARD;
        Map<String, Serializable> card = new HashMap();
        card.put("card_id", cardId);
        card.put("code", code);
        card.put("openid", openid);
        card.put("expire_seconds", expireSeconds);
        card.put("is_unique_code", uniqueCode);
        card.put("outer_id", outerId);

        this.actionInfo = new HashMap<>();
        this.actionInfo.put("card", card);

        this.cardId = cardId;
        this.outerId = outerId;
        this.code = code;
        this.openid = openid;
        this.expireSeconds = expireSeconds;
        this.uniqueCode = uniqueCode;
    }

    public Serializable generateScene() {
        if(this.actionName == Constants.QrcodeType.QR_LIMIT_SCENE) {
            Serializable secene = actionInfo.get("scene").get("scene_str");
            if(secene == null) {
                secene = actionInfo.get("scene").get("scene_id");
            }
            return secene;
        } else if(this.actionName == Constants.QrcodeType.QR_CARD) {
            Serializable secene = actionInfo.get("card").get("outer_id");
            return secene;
        }
        Serializable secene = actionInfo.get("scene").get("scene_id");
        return secene;
    }

    public Constants.QrcodeType getActionName() {
        return actionName;
    }

    public void setActionName(Constants.QrcodeType actionName) {
        this.actionName = actionName;
    }

    public Map<String, Map<String, Serializable>> getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(Map<String, Map<String, Serializable>> actionInfo) {
        this.actionInfo = actionInfo;
    }


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Integer getOuterId() {
        return outerId;
    }

    public void setOuterId(Integer outerId) {
        this.outerId = outerId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(Integer expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public Boolean getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(Boolean uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
}

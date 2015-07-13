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
    private String actionName;//二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值

    @JsonProperty("action_info")
    @JSONField(name = "action_info")
    private Map<String, Map<String, Object>> actionInfo;//二维码详细信息


    public ReqTicket(Constants.QrcodeType actionName, String sceneStr) {
        this.actionName = actionName.toString();
        Map<String, Object> scenet = new HashMap();
        scenet.put("scene_str", sceneStr);

        this.actionInfo = new HashMap<String, Map<String, Object>>();
        this.actionInfo.put("scene", scenet);
    }

    public ReqTicket(Constants.QrcodeType actionName, int sceneId) {
        this.actionName = actionName.toString();
        Map<String, Object> scenet = new HashMap();
        scenet.put("scene_id", sceneId);

        this.actionInfo = new HashMap<String, Map<String, Object>>();
        this.actionInfo.put("scene", scenet);
    }

    public String generateSceneStr() {
        Object seceneStr = actionInfo.get("scene").get("scene_str");
        return seceneStr != null ? seceneStr.toString() : null;
    }

    public int generateSceneId() {
        Object sceneId =actionInfo.get("scene").get("scene_id");
        return sceneId != null ? Integer.parseInt(sceneId.toString()) : 0;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Map<String, Map<String, Object>> getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(Map<String, Map<String, Object>> actionInfo) {
        this.actionInfo = actionInfo;
    }
}

package com.fenlibao.p2p.weixin.service;

import com.fenlibao.p2p.common.service.ServiceTemplate;
import com.fenlibao.p2p.weixin.domain.Qrcode;

/**
 * Created by Administrator on 2015/6/18.
 */
public interface QrcodeService extends ServiceTemplate<Long,Qrcode> {

    /**
     * 根据scene字符串获取永久二维码
     * @param sceneStr
     * @return
     */
    Qrcode selectLimitSceneBySceneStr(String sceneStr, String scene);

    /**
     * 根据scene id获取永久二维码
     * @param sceneId
     * @return
     */
    Qrcode selectLimitSceneBySceneId(Integer sceneId, String scene);
}

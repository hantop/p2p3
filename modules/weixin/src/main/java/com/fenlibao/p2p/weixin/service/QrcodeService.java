package com.fenlibao.p2p.weixin.service;

import com.fenlibao.p2p.common.service.ServiceTemplate;
import com.fenlibao.p2p.weixin.domain.Qrcode;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/6/18.
 */
public interface QrcodeService extends ServiceTemplate<Long,Qrcode> {

    /**
     * 根据scene字符串获取永久二维码
     * @param sceneValue
     * @return
     */
    Qrcode selectLimitSceneByScene(Serializable sceneValue, String scene);

}

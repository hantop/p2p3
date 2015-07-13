package com.fenlibao.p2p.zhaopin.service;

import com.fenlibao.p2p.common.service.ServiceTemplate;
import com.fenlibao.p2p.weixin.domain.Qrcode;
import com.fenlibao.p2p.zhaopin.domain.Channel;

/**
 * Created by Administrator on 2015/6/18.
 */
public interface ChannelService extends ServiceTemplate<Long, Channel> {
    Qrcode getQrLimitScene(Integer sceneId);

    Channel selectBySceneId(int sceneId);


}

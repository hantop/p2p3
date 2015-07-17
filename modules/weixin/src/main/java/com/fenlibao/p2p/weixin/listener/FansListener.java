package com.fenlibao.p2p.weixin.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fenlibao.p2p.weixin.domain.Fans;
import com.fenlibao.p2p.weixin.event.FansEvent;
import com.fenlibao.p2p.weixin.exception.WeixinException;
import com.fenlibao.p2p.weixin.service.WxApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by Administrator on 2015/7/10.
 */
@Component
public class FansListener implements ApplicationListener<FansEvent> {

    @Inject
    private WxApi wxApi;
    private static final Logger log = LoggerFactory.getLogger(FansListener.class);

    @Override
    public void onApplicationEvent(FansEvent event) {
        try {
            Fans fans = wxApi.getFans(event.getOpenid());
            if (log.isInfoEnabled()) {
                log.info("获取用户信息:" + JSON.toJSONString(fans,SerializerFeature.PrettyFormat ,SerializerFeature.WriteClassName));

            }
        } catch (WeixinException e) {
            e.printStackTrace();
        }
    }
}

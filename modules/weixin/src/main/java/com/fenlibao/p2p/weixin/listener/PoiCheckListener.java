package com.fenlibao.p2p.weixin.listener;

import com.fenlibao.p2p.weixin.defines.PoiCheck;
import com.fenlibao.p2p.weixin.event.PoiCheckEvent;
import com.fenlibao.p2p.weixin.message.Message;
import com.fenlibao.p2p.weixin.proxy.WeixinProxy;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by Administrator on 2015/7/13.
 */
@Component
public class PoiCheckListener implements ApplicationListener<PoiCheckEvent> {


    @Inject
    private WeixinProxy weixinProxy;

    @Override
    public void onApplicationEvent(PoiCheckEvent poiCheckEvent) {
        Message message = poiCheckEvent.getMessage();
        if (message.getResult().equals(PoiCheck.SUCCESS.toString())) {
            //审核成功
            weixinProxy.httpPoi(message.getPoiId());
        }
    }
}

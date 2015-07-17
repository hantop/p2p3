package com.fenlibao.p2p.weixin.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fenlibao.p2p.weixin.domain.*;
import com.fenlibao.p2p.weixin.event.LogEvent;
import com.fenlibao.p2p.weixin.message.Poi;
import com.fenlibao.p2p.weixin.persistence.LogMapper;
import com.fenlibao.p2p.weixin.service.*;
import com.fenlibao.p2p.weixin.variable.WeiXinThing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by Administrator on 2015/7/6.
 */
@Component
public class LogListener implements ApplicationListener<LogEvent> {

    private static final Logger log = LoggerFactory.getLogger(LogListener.class);
    @Inject
    private LogMapper logMapper;

    @Inject
    private TokenService tokenService;

    @Inject
    private FansService fansService;

    @Inject
    private TicketService ticketService;

    @Inject
    private QrcodeService qrcodeService;

    @Inject
    private BusinessService businessService;

    @Override
    public void onApplicationEvent(LogEvent event) {
        Log log = event.getLog();
        String thingName = log.getThing();
        Object returnValue = event.getReturnValue();
        logMapper.insertSelective(log);
        if(LogListener.log.isInfoEnabled()) {
            LogListener.log.info("微信执行日志消息通知保存:{}", JSON.toJSONString(log, SerializerFeature.PrettyFormat ,SerializerFeature.WriteClassName));
        }
        if (thingName.equals(WeiXinThing.HTTP_TOKEN.toString())) {
            //token
            Token token = (Token) returnValue;
            if(token.getId() == null) {
                token.setId(getUUID());
                token.setLogId(log.getId());
                tokenService.insertSelective(token);//tokeny保存
            }
        } else if (thingName.equals(WeiXinThing.HTTP_FANS.toString())) {
            //fans
            Fans fans = (Fans)returnValue;
            if(fans.getId() == null) {
                fans.setId(this.getUUID());
                fans.setLogId(log.getId());
                fansService.saveOrUpdate(fans);//fans
            }
        } else if (thingName.equals(WeiXinThing.HTTP_TICKET.toString())) {
            //ticket
            Ticket ticket = (Ticket)returnValue;
            if(ticket.getId() == null) {
                ticket.setLogId(log.getId());
                ticket.setId(this.getUUID());
                ticketService.insertSelective(ticket);
            }
        } else if (thingName.equals(WeiXinThing.HTTP_QRCODE.toString())) {
            //qrcode
            Qrcode qrcode = (Qrcode)returnValue;
            if(qrcode.getId() == null) {
                qrcode.setId(this.getUUID());
                qrcode.setLogId(log.getId());
                this.qrcodeService.insertSelective(qrcode);
            }
        } else if(thingName.equals(WeiXinThing.HTTP_POI.toString())) {
            Poi poi = (Poi)returnValue;
            if(poi.getBusiness() != null && poi.getBusiness().getBaseInfo() != null) {
                Business business = poi.getBusiness().getBaseInfo();
                this.businessService.saveOrUpdateByPoiId(business);
            }
        }
    }

    private String getUUID() {
        return UUID.randomUUID().toString();
    }
}

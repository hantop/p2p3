package com.fenlibao.p2p.weixin.listener;

import com.fenlibao.p2p.weixin.domain.Msg;
import com.fenlibao.p2p.weixin.event.MsgEvent;
import com.fenlibao.p2p.weixin.message.Message;
import com.fenlibao.p2p.weixin.persistence.MsgMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by Administrator on 2015/7/9.
 */
@Component
public class MsgListener implements ApplicationListener<MsgEvent> {

    @Autowired
    private MsgMapper msgMapper;

    @Async
    @Override
    public void onApplicationEvent(MsgEvent event) {
        String id = getUUID();
        Message message = event.getMessage();
        Msg msg = new Msg();
        msg.setId(id);
        BeanUtils.copyProperties(message,msg);
        msg.setAppId(event.getAppId());
        msgMapper.insertSelective(msg);
    }

    private String getUUID() {
        return UUID.randomUUID().toString();
    }
}

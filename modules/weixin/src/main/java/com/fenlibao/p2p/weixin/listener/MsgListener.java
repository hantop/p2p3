package com.fenlibao.p2p.weixin.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fenlibao.p2p.weixin.defines.MsgType;
import com.fenlibao.p2p.weixin.domain.Media;
import com.fenlibao.p2p.weixin.domain.Msg;
import com.fenlibao.p2p.weixin.event.MsgEvent;
import com.fenlibao.p2p.weixin.message.Item;
import com.fenlibao.p2p.weixin.message.Message;
import com.fenlibao.p2p.weixin.persistence.MediaMapper;
import com.fenlibao.p2p.weixin.persistence.MsgMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2015/7/9.
 */
@Component
public class MsgListener implements ApplicationListener<MsgEvent> {
    private static final Logger log = LoggerFactory.getLogger(MsgListener.class);
    @Inject
    private MsgMapper msgMapper;

    @Inject
    private MediaMapper mediaMapper;

    @Async
    @Override
    public void onApplicationEvent(MsgEvent event) {
        if (log.isInfoEnabled()) {
            log.info("用户、微信交互信息:{}", JSON.toJSONString(event, SerializerFeature.PrettyFormat ,SerializerFeature.WriteClassName));
        }

        String id = getUUID();
        Message message = event.getMessage();
        Msg msg = new Msg();
        msg.setId(id);
        BeanUtils.copyProperties(message, msg);
        msg.setAppId(event.getAppId());
        msgMapper.insertSelective(msg);
        com.fenlibao.p2p.weixin.domain.Media domain = new Media();
        domain.setMessageId(msg.getId());
        com.fenlibao.p2p.weixin.message.Media weixinMsg = null;
        if (message.getImage() != null) {//图片
            weixinMsg = message.getImage();
            domain.setType(MsgType.MESSAGETYPE_IMAGE);
        } else if (message.getVoice() != null) {
            weixinMsg = message.getVoice();//语音消息
            domain.setType(MsgType.MESSAGETYPE_VOICE);
        } else if (message.getVideo() != null) {
            weixinMsg = message.getVideo();//视频消息
            domain.setType(MsgType.MESSAGETYPE_VIDEO);
        } else if (message.getMusic() != null) {
            weixinMsg = message.getMusic();//音乐消息
            domain.setType(MsgType.MESSAGETYPE_MUSIC);
        } else if (message.getArticles() != null && !message.getArticles().isEmpty()) {
            List<Item> itemList = message.getArticles();
            for (Item item : itemList) {
                BeanUtils.copyProperties(item, domain);
                item.setType(MsgType.MESSAGETYPE_NEWS);
                item.setMessageId(msg.getId());
                item.setId(getUUID());
            }
            mediaMapper.insertBatch(itemList);
            if (log.isInfoEnabled()) {
                log.info("保存微信用户交互的多媒体信息:{}", JSON.toJSONString(itemList, SerializerFeature.PrettyFormat ,SerializerFeature.WriteClassName));
            }
            return;
        }
        if (domain.getType() != null) {
            BeanUtils.copyProperties(weixinMsg, domain);
            mediaMapper.insertSelective(domain);
        }
        if (log.isInfoEnabled()) {
            log.info("保存微信用户交互的多媒体信息:{}", JSON.toJSONString(domain, SerializerFeature.PrettyFormat ,SerializerFeature.WriteClassName));
        }
    }

    private String getUUID() {
        return UUID.randomUUID().toString();
    }
}

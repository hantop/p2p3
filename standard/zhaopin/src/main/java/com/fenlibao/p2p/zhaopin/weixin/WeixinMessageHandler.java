package com.fenlibao.p2p.zhaopin.weixin;

import com.fenlibao.p2p.weixin.defines.Event;
import com.fenlibao.p2p.weixin.defines.MsgType;
import com.fenlibao.p2p.weixin.domain.Fans;
import com.fenlibao.p2p.weixin.message.Item;
import com.fenlibao.p2p.weixin.message.Message;
import com.fenlibao.p2p.weixin.service.Constants;
import com.fenlibao.p2p.weixin.service.MessageHandler;
import com.fenlibao.p2p.weixin.service.WxApi;
import com.fenlibao.p2p.zhaopin.domain.Channel;
import com.fenlibao.p2p.zhaopin.domain.Relationship;
import com.fenlibao.p2p.zhaopin.persistence.RelationshipMapper;
import com.fenlibao.p2p.zhaopin.service.ChannelService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/18.
 */
@Component("messageHandler")
public class WeixinMessageHandler implements MessageHandler,Constants {


    @Inject
    private ChannelService channelService;

    @Inject
    private RelationshipMapper relationshipMapper;



    @Override
    public Message scanEvent(Message message,WxApi wxApi,String host) {
        String event = message.getEvent();
        String eventKey = message.getEventKey();
        /**
         * 用户扫描事件，分为已关注扫描和未关注扫描
         * 用户未关注时，EventKey	事件KEY值，qrscene_为前缀，后面为二维码的参数值
         * 用户已关注时,事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
         */
        String sceneStr = eventKey;
        if (sceneStr.startsWith(Event.EVENT_KEY_QRSCENE.toString()) && event.equals(Event.EVENT_SUBSCRIBE.toString())) {
            sceneStr = sceneStr.substring(Event.EVENT_KEY_QRSCENE.toString().length());
        }
        int sceneId = Integer.parseInt(sceneStr);
        Channel channel = this.channelService.selectBySceneId(sceneId);

        String fromUserName = message.getFromUserName();
        String url = channel.getUrl();

        host = host + WxApi.TOW_LEVEL_DOMAIN_URL;

        String redirectUrl = String.format(host, "zhaopin/zhaopin");
//        String state = "fromUserName" + Constants.assignment + fromUserName + Constants.separator +"id" + Constants.assignment + channel.getId() + Constants.separator + "redirect" + Constants.assignment + url;
        String state = Constants.FROM_USER_NAME + Constants.assignment + fromUserName + Constants.separator +"id" + Constants.assignment + channel.getId() + Constants.separator + "redirect" + Constants.assignment + url;
//        state = state + Constants.separator + "title" + Constants.assignment + "哎呀呀招聘" + Constants.separator + "imgUrl" + Constants.assignment + "http://img5.imgtn.bdimg.com/it/u=747474479,3247936386&fm=21&gp=0.jpg" + Constants.separator + "desc" + Constants.assignment + "哎呀呀招聘";

        Message result = new Message();
        result.setFromUserName(message.getToUserName());
        result.setToUserName(message.getFromUserName());
        result.setCreateTime(System.currentTimeMillis());
        result.setMsgType(MsgType.MESSAGETYPE_NEWS.toString());

        Item item = new Item();
        item.setTitle(channel.getName());
        item.setDescription(channel.getName());
        item.setPicUrl("http://img5.imgtn.bdimg.com/it/u=747474479,3247936386&fm=21&gp=0.jpg");
        item.setUrl(wxApi.generateOauth2Url(redirectUrl, state));

        List<Item> items = new ArrayList<>();
        items.add(item);
        result.setArticles(items);
        result.setArticleCount(items.size());
        return result;
    }

    @Override
    public Message oauth2Event(Fans userInfo, String state) {

        String[] params = state.split(Constants.separator);
        String fromUserName = params[0].split("=")[1];
        Long id = Long.parseLong(params[1].split("=")[1]);
        String toUserName = userInfo.getOpenid();

        Channel channel = channelService.selectByPrimaryKey(id);

        Relationship relationship = this.relationshipMapper.selectByFromUserAndToUser(fromUserName,toUserName);
        if(relationship == null && !toUserName.equals(fromUserName)) {//如果是同一个人不形成推荐关系
            relationship = new Relationship();
            relationship.setFromUserOpenid(fromUserName);
            relationship.setToUserOpenid(toUserName);
            relationship.setPositiveStatus(false);
            relationship.setTryStatus(false);
            relationship.setTrialPrice(channel.getRule().getTrialSuccess());
            relationship.setPositivePrice(channel.getRule().getPositiveSuccess());
            this.relationshipMapper.insertSelective(relationship);
        }
        return null;
    }
}

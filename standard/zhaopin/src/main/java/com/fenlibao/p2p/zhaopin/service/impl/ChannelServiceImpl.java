package com.fenlibao.p2p.zhaopin.service.impl;

import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.constant.domain.Constant;
import com.fenlibao.p2p.constant.service.ConstantService;
import com.fenlibao.p2p.weixin.domain.Qrcode;
import com.fenlibao.p2p.weixin.exception.WeixinException;
import com.fenlibao.p2p.weixin.service.WxApi;
import com.fenlibao.p2p.zhaopin.domain.Channel;
import com.fenlibao.p2p.zhaopin.persistence.ChannelMapper;
import com.fenlibao.p2p.zhaopin.service.ChannelService;
import com.fenlibao.p2p.zhaopin.service.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/6/18.
 */
@Service("channelService")
public class ChannelServiceImpl implements ChannelService, ApplicationListener<ContextRefreshedEvent> ,Constants {

    @Autowired
    private ChannelMapper channelMapper;

    @Autowired
    private ConstantService constantService;

    @Autowired
    private WxApi wxApi;

    private Constant constant;

    @Override
    public Page<Channel> findPage(Page<Channel> page) {
        List<Channel> channels = this.channelMapper.findPage(page);
        page.addContent(channels);
        return page;
    }


    @Override
    public int insert(Channel record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Channel record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Channel record) {
        return this.channelMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insertSelective(Channel record) {
        int count = this.channelMapper.insertSelective(record);
        int sceneId = Integer.parseInt(record.getId() + "");
        try {
            Qrcode qrcode = this.wxApi.getQrLimitScene(sceneId, constant.getValue());
        } catch (WeixinException e) {
            e.printStackTrace();
        }
        record.setSceneId(sceneId);
        this.channelMapper.updateByPrimaryKeySelective(record);
        return count;
    }

    @Override
    public int saveOrUpdate(Channel record) {
        return 0;
    }

    @Override
    public Channel selectByPrimaryKey(Long id) {
        return this.channelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Channel> findAll() {
        return null;
    }

    @Override
    public Qrcode getQrLimitScene(Integer sceneId) {
        Qrcode qrcode = null;
        try {
            qrcode = this.wxApi.getQrLimitScene(sceneId, constant.getValue());
        } catch (WeixinException e) {
            e.printStackTrace();
        }
        return qrcode;
    }

    @Override
    public Channel selectBySceneId(int sceneId) {
        return this.channelMapper.selectBySceneId(sceneId);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.constant = constantService.selectByKey(SCENE_ZHAOPIN);
    }
}
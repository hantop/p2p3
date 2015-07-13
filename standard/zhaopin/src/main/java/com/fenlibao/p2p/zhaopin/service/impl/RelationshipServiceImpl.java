package com.fenlibao.p2p.zhaopin.service.impl;

import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.weixin.service.WxApi;
import com.fenlibao.p2p.zhaopin.domain.Relationship;
import com.fenlibao.p2p.zhaopin.domain.RelationshipHistory;
import com.fenlibao.p2p.zhaopin.persistence.RelationshipMapper;
import com.fenlibao.p2p.zhaopin.service.ChannelService;
import com.fenlibao.p2p.zhaopin.service.RelationshipHistoryService;
import com.fenlibao.p2p.zhaopin.service.RelationshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2015/6/18.
 */
@Service("relationshipService")
public class RelationshipServiceImpl implements RelationshipService {

    private final static Logger log = LoggerFactory.getLogger(RelationshipServiceImpl.class);

    @Autowired
    private RelationshipMapper relationshipMapper;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private WxApi wxApi;

    @Autowired
    private RelationshipHistoryService relationshipHistoryService;

    @Override
    public Page<Relationship> findPage(Page<Relationship> page) {
        List<Relationship> relationships = this.relationshipMapper.findPage(page);
        page.addContent(relationships);
        return page;
    }

    @Override
    public int insert(Relationship record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Relationship record) {
        return this.relationshipMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Relationship record) {
        return this.relationshipMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insertSelective(Relationship record) {
        return this.relationshipMapper.insertSelective(record);
    }

    @Override
    public int saveOrUpdate(Relationship record) {
        return 0;
    }

    @Override
    public Relationship selectByPrimaryKey(Long id) {
        return this.relationshipMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Relationship> findAll() {
        return null;
    }

    @Override
    public int tryStatus(Long id) {
        Relationship relationship = this.selectByPrimaryKey(id);
        relationship.setTryStatus(!relationship.getTryStatus());
       int count = this.updateByPrimaryKeySelective(relationship);
        RelationshipHistory history = new RelationshipHistory();
        history.setProvide(true);
        history.setRelationshipId(id);
        history.setProvideTime(new Timestamp(System.currentTimeMillis()));
        history.setOperator(this.getUserDetails().getUsername());
        history.setProvidePrice(relationship.getTrialPrice());//试用价格
        relationshipHistoryService.insertSelective(history);
        return count;
    }

    @Override
    public int positiveStatus(Long id) {
        Relationship relationship = this.selectByPrimaryKey(id);
        relationship.setPositiveStatus(!relationship.getPositiveStatus());
        int count = this.updateByPrimaryKeySelective(relationship);
        RelationshipHistory history = new RelationshipHistory();
        history.setProvide(true);
        history.setRelationshipId(id);
        history.setProvideTime(new Timestamp(System.currentTimeMillis()));
        history.setOperator(this.getUserDetails().getUsername());
        history.setProvidePrice(relationship.getPositivePrice());//转正价格
        relationshipHistoryService.insertSelective(history);
        return count;
    }

    @Override
    public Relationship selectByFromUserAndToUser(String fromUserName, String toUserName) {
        return this.relationshipMapper.selectByFromUserAndToUser(fromUserName,toUserName);
    }

    /**
     * 获取当前登录者的用户信息
     * @return
     */
    private UserDetails getUserDetails() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return userDetails;
    }

//    @Async
//    @Override
//    public void onApplicationEvent(OauthEvent event) {
//        Fans fans = event.getFans();
//        String state = event.getState();
//        String[] params = state.split(Constants.separator);
//        String fromUserName = params[0].split("=")[1];
//        Long id = Long.parseLong(params[1].split("=")[1]);
//        String toUserName = fans.getOpenid();
//
//        Channel channel = channelService.selectByPrimaryKey(id);
//
//        Relationship relationship = this.relationshipMapper.selectByFromUserAndToUser(fromUserName,toUserName);
//        if(relationship == null && !toUserName.equals(fromUserName)) {//如果是同一个人不形成推荐关系
//            relationship = new Relationship();
//            relationship.setFromUserOpenid(fromUserName);
//            relationship.setToUserOpenid(toUserName);
//            relationship.setPositiveStatus(false);
//            relationship.setTryStatus(false);
//            relationship.setTrialPrice(channel.getRule().getTrialSuccess());
//            relationship.setPositivePrice(channel.getRule().getPositiveSuccess());
//            this.insertSelective(relationship);
//        }
//
//    }
}

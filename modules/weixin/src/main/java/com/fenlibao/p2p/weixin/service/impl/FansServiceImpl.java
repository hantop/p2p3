package com.fenlibao.p2p.weixin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.weixin.domain.Fans;
import com.fenlibao.p2p.weixin.persistence.FansMapper;
import com.fenlibao.p2p.weixin.service.FansService;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Administrator on 2015/6/4.
 */
@Service("fansService")
public class FansServiceImpl implements FansService {

    private static final Logger log = LoggerFactory.getLogger(FansServiceImpl.class);

    @Inject
    private FansMapper fansMapper;

    @Override
    public Page<Fans> findPage(Page<Fans> page) {
        return null;
    }

    @Override
    public int insert(Fans record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Fans record) {
        return fansMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Fans record) {
        return fansMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insertSelective(Fans record) {
        return this.fansMapper.insertSelective(record);
    }

    @Override
    public Fans selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<Fans> findAll() {
        return null;
    }

    @Override
    public Fans selectByOpenId(String openId) {
        return this.fansMapper.selectByOpenId(openId);
    }

    @Override
    public int updateByOpenIdSelective(Fans record) {
        return this.fansMapper.updateByOpenIdSelective(record);
    }

    @Override
    public int saveOrUpdate(Fans record) {
        if(log.isInfoEnabled()) {
            log.info("保存获取更新微信fans信息:{}", JSON.toJSONString(record, SerializerFeature.PrettyFormat, SerializerFeature.WriteClassName));
        }
        Fans flag = this.fansMapper.selectByOpenId(record.getOpenid());
        if(flag != null) {
            return this.fansMapper.updateByOpenIdSelective(record);
        } else {
            return this.fansMapper.insertSelective(record);
        }
    }
}

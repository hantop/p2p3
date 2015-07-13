package com.fenlibao.p2p.weixin.service.impl;

import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.weixin.domain.Fans;
import com.fenlibao.p2p.weixin.persistence.FansMapper;
import com.fenlibao.p2p.weixin.service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/6/4.
 */
@Service("fansService")
public class FansServiceImpl implements FansService {

    @Autowired
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
        Fans flag = this.fansMapper.selectByOpenId(record.getOpenid());
        if(flag != null) {
            return this.fansMapper.updateByOpenIdSelective(record);
        } else {
            return this.fansMapper.insertSelective(record);
        }
    }
}

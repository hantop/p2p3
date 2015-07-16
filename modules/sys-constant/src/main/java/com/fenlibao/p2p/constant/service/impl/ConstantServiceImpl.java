package com.fenlibao.p2p.constant.service.impl;

import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.constant.domain.Constant;
import com.fenlibao.p2p.constant.persistence.ConstantMapper;
import com.fenlibao.p2p.constant.service.ConstantService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Administrator on 2015/6/30.
 */
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Service("constantService")
public class ConstantServiceImpl implements ConstantService {

    @Resource
    private ConstantMapper constantMapper;

    @Override
    public Page<Constant> findPage(Page<Constant> page) {
        return null;
    }

    @Override
    public int insert(Constant record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Constant record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Constant record) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insertSelective(Constant record) {
        return 0;
    }

    @Override
    public int saveOrUpdate(Constant record) {
        return 0;
    }

    @Override
    public Constant selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<Constant> findAll() {
        return this.constantMapper.findAll();
    }

    @Override
    public Constant selectByKey(String key) {
        return this.constantMapper.selectByKey(key);
    }

    @Override
    public List<Constant> selectByType(Enum type) {
        return this.constantMapper.selectByType(type);
    }
}

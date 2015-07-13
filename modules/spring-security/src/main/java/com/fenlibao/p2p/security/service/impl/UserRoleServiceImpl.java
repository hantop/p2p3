package com.fenlibao.p2p.security.service.impl;

import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.security.domain.UserRole;
import com.fenlibao.p2p.security.persistence.UserRoleMapper;
import com.fenlibao.p2p.security.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/6/12.
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Page<UserRole> findPage(Page<UserRole> page) {
        return null;
    }

    @Override
    public int insert(UserRole record) {
        return userRoleMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(UserRole record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(UserRole record) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insertSelective(UserRole record) {
        return userRoleMapper.insertSelective(record);
    }

    @Override
    public int saveOrUpdate(UserRole record) {
        return 0;
    }

    @Override
    public UserRole selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<UserRole> findAll() {
        return null;
    }
}

package com.fenlibao.p2p.security.service.impl;

import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.security.domain.Role;
import com.fenlibao.p2p.security.persistence.RoleMapper;
import com.fenlibao.p2p.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/6/11.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Page<Role> findPage(Page<Role> page) {
        return null;
    }

    @Override
    public int insert(Role record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insertSelective(Role record) {
        return 0;
    }

    @Override
    public int saveOrUpdate(Role record) {
        return 0;
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }
}

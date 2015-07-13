package com.fenlibao.p2p.security.persistence;

import com.fenlibao.p2p.security.domain.Role;

import java.util.List;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);

    List<Role> findAll();
}
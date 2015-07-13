package com.fenlibao.p2p.security.persistence;

import com.fenlibao.p2p.security.domain.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}
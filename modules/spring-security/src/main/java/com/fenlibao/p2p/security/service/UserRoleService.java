package com.fenlibao.p2p.security.service;

import com.fenlibao.p2p.common.service.ServiceTemplate;
import com.fenlibao.p2p.security.domain.UserRole;

/**
 * Created by Administrator on 2015/6/12.
 */
public interface UserRoleService extends ServiceTemplate<Long,UserRole>{

    int insert(UserRole record);

    int insertSelective(UserRole record);
}

package com.fenlibao.p2p.security.service;

import com.fenlibao.p2p.common.service.ServiceTemplate;
import com.fenlibao.p2p.security.domain.Role;

import java.util.List;

/**
 * Created by Administrator on 2015/6/11.
 */
public interface RoleService extends ServiceTemplate<Long,Role>{

    /**
     * 获取所有的角色资源信息
     * @return
     */
    List<Role> findAll();

}

package com.fenlibao.p2p.security.service.impl;

import com.fenlibao.p2p.security.domain.User;
import com.fenlibao.p2p.security.domain.UserRole;
import com.fenlibao.p2p.security.error.UserException;
import com.fenlibao.p2p.security.persistence.UserMapper;
import com.fenlibao.p2p.security.service.UserRoleService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * Created by Administrator on 2015/6/11.
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Inject
    UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }

    public int insertSelective(User user) {

        UserDetails userDetails = userMapper.findByUsername(user.getUsername());
        if (userDetails == null) {
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpire(true);
            user.setEnabled(true);
            int flag = this.userMapper.insertSelective(user);
            Long userId = user.getId();
            Long roleId = 1L;
            UserRole userRole = new UserRole(userId, roleId);
            return userRoleService.insertSelective(userRole);
        }
        throw new UserException("用户名已经被注册过了");
    }
}

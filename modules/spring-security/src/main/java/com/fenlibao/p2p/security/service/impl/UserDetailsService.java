package com.fenlibao.p2p.security.service.impl;

import com.fenlibao.p2p.security.domain.Regex;
import com.fenlibao.p2p.security.domain.Role;
import com.fenlibao.p2p.security.domain.User;
import com.fenlibao.p2p.security.domain.UserRole;
import com.fenlibao.p2p.security.error.UserException;
import com.fenlibao.p2p.security.persistence.RegexMapper;
import com.fenlibao.p2p.security.persistence.UserMapper;
import com.fenlibao.p2p.security.service.UserRoleService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.*;

/**
 * Created by Administrator on 2015/6/11.
 */
@DependsOn("flyway")
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Inject
    private RegexMapper regexMapper;

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

//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
//            rollbackFor = {java.lang.Exception.class, java.lang.RuntimeException.class})
    public int insertSelective(User user) {

        UserDetails userDetails = userMapper.findByUsername(user.getUsername());
        if (userDetails == null) {
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpire(true);
            user.setEnabled(true);
            this.userMapper.insertSelective(user);
            Long userId = user.getId();
            Long roleId = 1L;
            UserRole userRole = new UserRole(userId, roleId);
            return userRoleService.insertSelective(userRole);
        }
        throw new UserException("用户名已经被注册过了");
    }

//    public List<Regex> requestMap() {
//        Map<RequestMatcher, Collection<ConfigAttribute>> requestMap = new HashMap<>();
//        List<Regex> regexes = regexMapper.findAll();
//        return regexes;
//    }

    public Map<RequestMatcher, Collection<ConfigAttribute>> requestMap() {
        Map<RequestMatcher, Collection<ConfigAttribute>> requestMap = new HashMap<>();
        List<Regex> regexes = regexMapper.findAll();
        for (Regex regex : regexes) {
            String url = regex.getRegex();
            RequestMatcher matcher = new AntPathRequestMatcher(url);
            List<String> authoritys = new ArrayList<>();
            for (Role role : regex.getRoles()) {
                String authority = role.getAuthority();
                authoritys.add(authority);
            }
            Collection<ConfigAttribute> configAttributes = SecurityConfig.createList(authoritys.toArray(new String[]{}));
            requestMap.put(matcher, configAttributes);
        }
        return requestMap;
    }
}

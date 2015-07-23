package com.fenlibao;

import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.header.HeaderWriterFilter;

/**
 * Created by Administrator on 2015/7/15.
 */
public class SecurityTest {

    FilterSecurityInterceptor filterSecurityInterceptor;
    FilterChainProxy filterChainProxy;
    HeaderWriterFilter headerWriterFilter;
    CsrfFilter csrfFilter;
    LogoutFilter logoutFilter;
    LogoutConfigurer configurer;
    UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;
    DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler;
}

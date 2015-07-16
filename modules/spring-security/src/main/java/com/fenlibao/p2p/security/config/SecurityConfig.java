package com.fenlibao.p2p.security.config;

import com.fenlibao.p2p.security.service.impl.UserDetailsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.AntPathRequestMatcher;

import javax.inject.Inject;

/**
 * Created by Administrator on 2015/6/19.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    private ApplicationContext context;

    @Inject
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/register").permitAll()
                .antMatchers("/**").hasRole("USER")
                .anyRequest()
                .fullyAuthenticated().and()
                .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/login_check")
                .failureUrl("/login?error").and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/weixin/**", "/zhaopin/**","/sign/**"); // 忽略任何以”/resources/”开头的请求，这和在XML配置http@security=none的效果一样
    }

    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }
}

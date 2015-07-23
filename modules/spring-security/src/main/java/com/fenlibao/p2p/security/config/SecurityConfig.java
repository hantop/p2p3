package com.fenlibao.p2p.security.config;

import com.fenlibao.p2p.security.service.impl.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.ReflectionUtils;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/19.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    private UserDetailsService userDetailsService;

    @Override
    public void init(WebSecurity web) throws Exception {
        web.debug(true);
        super.init(web);

    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/login", "/register").permitAll()
//                .antMatchers("/**").hasRole("ADMIN")
//                .anyRequest()
//                .fullyAuthenticated().and()
//                .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/login_check")
//                .failureUrl("/login?error").and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
//    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/weixin/**", "/zhaopin/**", "/sign/**"); // 忽略任何以”/resources/”开头的请求，这和在XML配置http@security=none的效果一样
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


    @Bean
    public RoleVoter roleVoter() {
        return new RoleHierarchyVoter(roleHierarchy());
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ADMIN > PREMIUM  PREMIUM > USER  USER > GUEST");
        return roleHierarchy;
    }


    //
//    @Bean
//    public RoleHierarchyVoter roleVoter() {
//        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER > ROLE_ANONYMOUS");
//        RoleHierarchyVoter roleHierarchyVoter = new RoleHierarchyVoter(roleHierarchy);
//        return roleHierarchyVoter;
//    }
//
//
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/login_check")
                .failureUrl("/login?error").and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/").and()
                .authorizeRequests()
//                .accessDecisionManager(accessDecisionManager())//.expressionHandler(defaultWebSecurityExpressionHandler())
                .antMatchers("/login", "/register").permitAll()
                .antMatchers("/**").hasRole("USER")
                .anyRequest().authenticated();
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
//                        fsi.setAccessDecisionManager(accessDecisionManager());
//                        FilterInvocationSecurityMetadataSource securityMetadataSource = fsi.getSecurityMetadataSource();
//                        Field field = ReflectionUtils.findField(securityMetadataSource.getClass(),"requestMap");
//                        field.setAccessible(true);
//                        Map<RequestMatcher, Collection<ConfigAttribute>> requestMap = (Map<RequestMatcher, Collection<ConfigAttribute>>) ReflectionUtils.getField(field, securityMetadataSource);
//
//                        Map<RequestMatcher, Collection<ConfigAttribute>> metadataSources = userDetailsService.requestMap();
//                        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> metadataSource : metadataSources.entrySet()) {
//                            RequestMatcher requestMatcher = metadataSource.getKey();
//                            Collection<ConfigAttribute> configAttributes = metadataSource.getValue();
//                            requestMap.put(requestMatcher,configAttributes);
//                        }
//                        fsi.setSecurityMetadataSource(securityMetadataSource);
//                        fsi.setPublishAuthorizationSuccess(true);
//                    return fsi;
//            }
//        });

    }


//    @Bean
//    public DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler() {
//        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
//        defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
//        return defaultWebSecurityExpressionHandler;
//    }


    /**************************************************************
     * accessDecisionManager
     **************************************************************/
    @Bean
    public AffirmativeBased accessDecisionManager() {
        List<AccessDecisionVoter> decisionVoters = new ArrayList<>();
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        decisionVoters.add(webExpressionVoter);
        decisionVoters.add(roleVoter());
        AffirmativeBased affirmativeBased = new AffirmativeBased(decisionVoters);
        affirmativeBased.setAllowIfAllAbstainDecisions(true);
        return affirmativeBased;
    }
}

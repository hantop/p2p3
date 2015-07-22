package com.fenlibao.p2p.security.config;

import com.fenlibao.p2p.security.service.impl.UserDetailsService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.intercept.AfterInvocationManager;
import org.springframework.security.access.intercept.AfterInvocationProviderManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.*;
import org.springframework.security.web.authentication.session.ChangeSessionIdAuthenticationStrategy;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfLogoutHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.header.HeaderWriter;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.security.web.header.writers.CacheControlHeadersWriter;
import org.springframework.security.web.header.writers.HstsHeaderWriter;
import org.springframework.security.web.header.writers.XContentTypeOptionsHeaderWriter;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.context.ServletContextAware;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2015/6/19.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements ServletContextAware {

    private String key;
    private ServletContext servletContext;

    @Inject
    private ApplicationEventPublisher eventPublisher;

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
                .accessDecisionManager(accessDecisionManager())
                .antMatchers("/login", "/register").permitAll()
                .antMatchers("/**").hasRole("USER")
                .antMatchers("/login", "/signup/email", "/logout", "/search", "/").permitAll()
                .anyRequest().authenticated();

    }


//    @Bean(name = "springSecurityFilterChain")
//    public FilterChainProxy portalFilterChainProxy() {
//        List<SecurityFilterChain> securityFilterChains = new ArrayList<SecurityFilterChain>();
//        securityFilterChains.add(securityFilterChain());
//        FilterChainProxy filterChainProxy = new FilterChainProxy(securityFilterChains);
//        return filterChainProxy;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain() {
        RequestMatcher requestMatcher = new AntPathRequestMatcher("/**");
        List<Filter> filters = new ArrayList<>();
        filters.add(webAsyncManagerIntegrationFilter());//0
        filters.add(securityContextPersistenceFilter());//1
        filters.add(headerWriterFilter());//2
        filters.add(csrfFilter());//3
        filters.add(logoutFilter());//4
        filters.add(usernamePasswordAuthenticationFilter());//5
        filters.add(requestCacheAwareFilter());//6
        filters.add(securityContextHolderAwareRequestFilter());//7
        filters.add(anonymousAuthenticationFilter());//8
        filters.add(sessionManagementFilter());//9
        filters.add(exceptionTranslationFilter());//10
        filters.add(filterSecurityInterceptor());//11
        return new DefaultSecurityFilterChain(requestMatcher, filters);
    }

    /**************************************************************
     * 0: WebAsyncManagerIntegrationFilter
     **************************************************************/
    @Bean
    public WebAsyncManagerIntegrationFilter webAsyncManagerIntegrationFilter() {
        WebAsyncManagerIntegrationFilter webAsyncManagerIntegrationFilter = new WebAsyncManagerIntegrationFilter();
        webAsyncManagerIntegrationFilter.setServletContext(servletContext);
        webAsyncManagerIntegrationFilter.setBeanName(webAsyncManagerIntegrationFilter.toString());
        return webAsyncManagerIntegrationFilter;
    }

    /**************************************************************
     * 1: SecurityContextPersistenceFilter
     **************************************************************/
    @Bean
    public SecurityContextPersistenceFilter securityContextPersistenceFilter() {
        SecurityContextPersistenceFilter persistenceFilter = new SecurityContextPersistenceFilter(httpSessionSecurityContextRepository());
        persistenceFilter.setServletContext(servletContext);
        persistenceFilter.setBeanName(persistenceFilter.toString());
        return persistenceFilter;
    }

    @Bean
    public HttpSessionSecurityContextRepository httpSessionSecurityContextRepository() {
        HttpSessionSecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
        securityContextRepository.setAllowSessionCreation(true);
        return securityContextRepository;
    }

    /****************************************************************
     * 2: HeaderWriterFilter
     ****************************************************************/
    @Bean
    public HeaderWriterFilter headerWriterFilter() {
        HeaderWriterFilter headerWriterFilter = new HeaderWriterFilter(Arrays.asList(
                new HeaderWriter[]{
                        xContentTypeOptionsHeaderWriter(),
                        xXssProtectionHeaderWriter(),
                        cacheControlHeadersWriter(),
                        hstsHeaderWriter(),
                        xFrameOptionsHeaderWriter()
                }));
        headerWriterFilter.setServletContext(servletContext);
        headerWriterFilter.setBeanName(headerWriterFilter.toString());
        return headerWriterFilter;
    }

    @Bean
    public HeaderWriter xContentTypeOptionsHeaderWriter() {
        HeaderWriter headerWriter = new XContentTypeOptionsHeaderWriter();
        return headerWriter;
    }

    @Bean
    public HeaderWriter xXssProtectionHeaderWriter() {
        HeaderWriter headerWriter = new XXssProtectionHeaderWriter();
        return headerWriter;
    }

    @Bean
    public HeaderWriter cacheControlHeadersWriter() {
        HeaderWriter headerWriter = new CacheControlHeadersWriter();
        return headerWriter;
    }

    @Bean
    public HeaderWriter hstsHeaderWriter() {
        HeaderWriter headerWriter = new HstsHeaderWriter();
        return headerWriter;
    }

    @Bean
    public HeaderWriter xFrameOptionsHeaderWriter() {
        HeaderWriter headerWriter = new XFrameOptionsHeaderWriter();
        return headerWriter;
    }

    /****************************************************************
     * 3: csrfFilter
     ****************************************************************/
    @Bean
    public CsrfFilter csrfFilter() {
        CsrfFilter csrfFilter = new CsrfFilter(httpSessionCsrfTokenRepository());
        csrfFilter.setServletContext(servletContext);
        csrfFilter.setBeanName(csrfFilter.toString());
        return csrfFilter;
    }

    @Bean
    public HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository() {
        HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
        return csrfTokenRepository;
    }

    /**************************************************************
     * 4: LogoutFilter
     **************************************************************/
    @Bean
    public LogoutHandler cookieClearingLogoutHandler() {
        LogoutHandler logoutHandler = new CookieClearingLogoutHandler();
        return logoutHandler;
    }

    @Bean
    public LogoutHandler csrfLogoutHandler() {
        LogoutHandler logoutHandler = new CsrfLogoutHandler(httpSessionCsrfTokenRepository());
        return logoutHandler;
    }

    @Bean
    public LogoutHandler securityContextLogoutHandler() {
        LogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        return logoutHandler;
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        LogoutSuccessHandler logoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
        return logoutSuccessHandler;
    }

    @Bean
    public LogoutFilter logoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter(logoutSuccessHandler(), logoutHandlers());
        logoutFilter.setServletContext(servletContext);
        return logoutFilter;
    }

    private LogoutHandler[] logoutHandlers() {
        return new LogoutHandler[]{cookieClearingLogoutHandler(), csrfLogoutHandler(), securityContextLogoutHandler()};
    }

    /**************************************************************
     * 5: UsernamePasswordAuthenticationFilter
     **************************************************************/
    @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() {
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
        usernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
        usernamePasswordAuthenticationFilter.setServletContext(servletContext);
        usernamePasswordAuthenticationFilter.setBeanName(usernamePasswordAuthenticationFilter.toString());
        return usernamePasswordAuthenticationFilter;
    }

    /**************************************************************
     * 6: RequestCacheAwareFilter
     **************************************************************/
    @Bean
    public RequestCacheAwareFilter requestCacheAwareFilter() {
        RequestCacheAwareFilter requestCacheAwareFilter = new RequestCacheAwareFilter();
        requestCacheAwareFilter.setServletContext(servletContext);
        requestCacheAwareFilter.setBeanName(requestCacheAwareFilter.toString());
        return requestCacheAwareFilter;
    }

    /**************************************************************
     * 7: SecurityContextHolderAwareRequestFilter
     **************************************************************/
    @Bean
    public SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter() {
        SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter = new SecurityContextHolderAwareRequestFilter();
        securityContextHolderAwareRequestFilter.setAuthenticationEntryPoint(loginUrlAuthenticationEntryPoint());
        securityContextHolderAwareRequestFilter.setAuthenticationManager(authenticationManager());
        securityContextHolderAwareRequestFilter.setLogoutHandlers(Arrays.asList(logoutHandlers()));
        securityContextHolderAwareRequestFilter.setServletContext(servletContext);
        securityContextHolderAwareRequestFilter.setBeanName(securityContextHolderAwareRequestFilter.toString());
        return securityContextHolderAwareRequestFilter;
    }

    @Bean
    public LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
        LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint("/login");
        return loginUrlAuthenticationEntryPoint;
    }


    /**************************************************************
     * 8: AnonymousAuthenticationFilter
     **************************************************************/
    @Bean
    public AnonymousAuthenticationFilter anonymousAuthenticationFilter() {
        AnonymousAuthenticationFilter anonymousAuthenticationFilter = new AnonymousAuthenticationFilter(getKey());
        anonymousAuthenticationFilter.setServletContext(servletContext);
        anonymousAuthenticationFilter.setBeanName(anonymousAuthenticationFilter.toString());
        return anonymousAuthenticationFilter;
    }

    /**************************************************************
     * 9: SessionManagementFilter
     **************************************************************/
    @Bean
    public SessionManagementFilter sessionManagementFilter() {
        SessionManagementFilter sessionManagementFilter = new SessionManagementFilter(httpSessionSecurityContextRepository(), sessionAuthenticationStrategy());
        sessionManagementFilter.setBeanName(sessionManagementFilter.toString());
        sessionManagementFilter.setServletContext(servletContext);
        return sessionManagementFilter;
    }

    @Bean
    public CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy() {
        CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy = new CompositeSessionAuthenticationStrategy(Arrays.asList(sessionAuthenticationStrategies()));
        return sessionAuthenticationStrategy;
    }

    @Bean
    public ChangeSessionIdAuthenticationStrategy changeSessionIdAuthenticationStrategy() {
        ChangeSessionIdAuthenticationStrategy changeSessionIdAuthenticationStrategy = new ChangeSessionIdAuthenticationStrategy();
        return changeSessionIdAuthenticationStrategy;
    }

    @Bean
    public CsrfAuthenticationStrategy csrfAuthenticationStrategy() {
        CsrfAuthenticationStrategy csrfAuthenticationStrategy = new CsrfAuthenticationStrategy(httpSessionCsrfTokenRepository());
        return csrfAuthenticationStrategy;
    }

    private SessionAuthenticationStrategy[] sessionAuthenticationStrategies() {
        return new SessionAuthenticationStrategy[]{changeSessionIdAuthenticationStrategy(), csrfAuthenticationStrategy()};
    }


    /**************************************************************
     * 10: ExceptionTranslationFilter
     **************************************************************/
    @Bean
    public ExceptionTranslationFilter exceptionTranslationFilter() {
        ExceptionTranslationFilter exceptionTranslationFilter = new ExceptionTranslationFilter(loginUrlAuthenticationEntryPoint());
        exceptionTranslationFilter.setBeanName(exceptionTranslationFilter.toString());
        exceptionTranslationFilter.setServletContext(servletContext);
        return exceptionTranslationFilter;
    }


    /**************************************************************
     * 11: FilterSecurityInterceptor
     **************************************************************/
    @Bean
    public FilterSecurityInterceptor filterSecurityInterceptor() {
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager());
        filterSecurityInterceptor.setAuthenticationManager(authenticationManager());
        filterSecurityInterceptor.setApplicationEventPublisher(eventPublisher);
        filterSecurityInterceptor.setObserveOncePerRequest(true);
//        filterSecurityInterceptor.setAfterInvocationManager(afterInvocationManager());
        return filterSecurityInterceptor;
    }

//    @Bean
//    public AfterInvocationManager afterInvocationManager() {
//        AfterInvocationManager afterInvocationManager = new AfterInvocationProviderManager();
//        return afterInvocationManager;
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

    /**************************************************************
     * AuthenticationManager
     **************************************************************/
    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> providers = Arrays.asList(new AuthenticationProvider[]{daoAuthenticationProvider(), anonymousAuthenticationProvider()});
        AuthenticationManager authenticationManager = new ProviderManager(providers);
        return authenticationManager;
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationProvider anonymousAuthenticationProvider() {
        AuthenticationProvider anonymousAuthenticationProvider = new AnonymousAuthenticationProvider(getKey());
        return anonymousAuthenticationProvider;
    }

    private String getKey() {
        if (key == null) {
            key = UUID.randomUUID().toString();
        }
        return key;
    }


    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}

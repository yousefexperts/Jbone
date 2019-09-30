package cn.jbone.cas.client;

import cn.jbone.cas.client.listener.JboneCasSessionListener;
import cn.jbone.cas.client.pac4j.handler.JboneCasLogoutHandler;
import cn.jbone.cas.client.realm.JboneCasRealm;
import cn.jbone.cas.client.session.JboneCasSessionDao;
import cn.jbone.cas.client.session.JboneCasSessionFactory;
import cn.jbone.cas.client.session.JboneSessionTicketStore;
import cn.jbone.configuration.JboneConfiguration;
import cn.jbone.sys.api.UserApi;
import io.buji.pac4j.context.ShiroSessionStore;
import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.filter.SecurityFilter;
import io.buji.pac4j.subject.Pac4jSubjectFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.*;


@Configuration
public class ShiroCasConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ShiroCasConfiguration.class);

    @Bean
    Config getConfig(JboneConfiguration jboneConfiguration,ShiroSessionStore sessionStore,JboneCasLogoutHandler jboneCasLogoutHandler){
        CasConfiguration casConfiguration = new CasConfiguration(jboneConfiguration.getCas().getCasServerUrl()+jboneConfiguration.getCas().getLoginUrl(), jboneConfiguration.getCas().getCasServerUrl());
        casConfiguration.setAcceptAnyProxy(true);
        casConfiguration.setLogoutHandler(jboneCasLogoutHandler);

        CasClient casClient = new CasClient(casConfiguration);
        casClient.setCallbackUrl(jboneConfiguration.getCas().getCurrentServerUrlPrefix() + jboneConfiguration.getCas().getCasFilterUrlPattern() + "?client_name=CasClient");
        casClient.setIncludeClientNameInCallbackUrl(false);

        Clients clients = new Clients(jboneConfiguration.getCas().getCurrentServerUrlPrefix() + jboneConfiguration.getCas().getCasFilterUrlPattern() + "?client_name=CasClient", casClient);
        Config config = new Config(clients);
        config.setSessionStore(sessionStore);

        return config;
    }

    @Bean
    ShiroSessionStore getJboneSessionStore(){
        return new ShiroSessionStore();
    }

    @Bean
    JboneSessionTicketStore getSessionTicketStore(StringRedisTemplate stringRedisTemplate,JboneConfiguration jboneConfiguration){
        JboneSessionTicketStore sessionTicketStore = new JboneSessionTicketStore();
        sessionTicketStore.setRedisTemplate(stringRedisTemplate);
        sessionTicketStore.setTimeout(jboneConfiguration.getCas().getClientSessionTimeout());
        return sessionTicketStore;
    }

    @Bean
    JboneCasLogoutHandler getJboneCasLogoutHandler(SessionManager sessionManager,JboneSessionTicketStore sessionTicketStore){
        JboneCasLogoutHandler handler = new JboneCasLogoutHandler(sessionTicketStore);
        handler.setDestroySession(true);
        handler.setSessionManager(sessionManager);
        return handler;
    }

    @Bean
    public JboneCasRealm getJboneCasRealm(EhCacheManager ehCacheManager, UserApi userApi, JboneConfiguration jboneConfiguration){
        JboneCasRealm realm = new JboneCasRealm(ehCacheManager, userApi, jboneConfiguration.getSys().getServerName());
        return realm;
    }

    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return em;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));

        return filterRegistration;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(JboneCasRealm jboneCasRealm,DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(jboneCasRealm);
        securityManager.setCacheManager(getEhCacheManager());
        securityManager.setSubjectFactory(new Pac4jSubjectFactory());
        securityManager.setSessionManager(sessionManager);

        return securityManager;
    }

    @Bean(name = "sessionManager")
    public DefaultWebSessionManager getDefaultWebSessionManager(SessionListener sessionListener, SessionDAO sessionDao, SessionFactory sessionFactory,JboneConfiguration jboneConfiguration,SimpleCookie cookie){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(jboneConfiguration.getCas().getClientSessionTimeout());
        sessionManager.setSessionValidationSchedulerEnabled(false);
        sessionManager.setSessionListeners(Arrays.asList(sessionListener));
        sessionManager.setSessionDAO(sessionDao);
        sessionManager.setSessionFactory(sessionFactory);
        sessionManager.setSessionIdCookie(cookie);
        return sessionManager;
    }

    @Bean(name = "sessionDao")
    public SessionDAO getSessionDao(StringRedisTemplate redisTemplate,JboneSessionTicketStore sessionTicketStore){
        JboneCasSessionDao sessionDao = new JboneCasSessionDao(redisTemplate);
        sessionDao.setSessionTicketStore(sessionTicketStore);
        return sessionDao;
    }

    @Bean
    public SimpleCookie getCookie(){
        SimpleCookie cookie = new SimpleCookie();
        cookie.setName("j_s_id");
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        return cookie;
    }


    @Bean(name = "sessionListener")
    public SessionListener getSessionListener(){
        return new JboneCasSessionListener();
    }

    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(JboneConfiguration jboneConfiguration){
        return new JboneCasSessionFactory(jboneConfiguration);
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean,JboneConfiguration jboneConfiguration){
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

        filterChainDefinitionMap.put(jboneConfiguration.getCas().getCasFilterUrlPattern(), "callback");
        filterChainDefinitionMap.put(jboneConfiguration.getCas().getLogoutUrl(),"logout");

        if(jboneConfiguration.getCas().getFilterChainDefinition() != null){
            filterChainDefinitionMap.putAll(jboneConfiguration.getCas().getFilterChainDefinition());
        }

        String common = filterChainDefinitionMap.get("/**");
        if(StringUtils.isBlank(common)){
            filterChainDefinitionMap.put("/**", "security");
        }else if(!common.equals("anon")){
            filterChainDefinitionMap.put("/**", "security," + common);
        }

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager, JboneConfiguration jboneConfiguration,StringRedisTemplate redisTemplate, DefaultWebSessionManager sessionManager, Config config) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl(jboneConfiguration.getCas().getEncodedLoginUrl());
        shiroFilterFactoryBean.setSuccessUrl(jboneConfiguration.getCas().getSuccessUrl());
        shiroFilterFactoryBean.setUnauthorizedUrl(jboneConfiguration.getCas().getUnauthorizedUrl());

        Map<String, Filter> filters = new HashMap<>();
        CallbackFilter callbackFilter = new CallbackFilter();
        callbackFilter.setConfig(config);
        callbackFilter.setDefaultUrl(jboneConfiguration.getCas().getSuccessUrl());
        filters.put("callback", callbackFilter);

        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setConfig(config);
        logoutFilter.setDefaultUrl(jboneConfiguration.getCas().getCurrentServerUrlPrefix() + jboneConfiguration.getCas().getCasFilterUrlPattern() + "?client_name=CasClient");
        logoutFilter.setCentralLogout(true);
        logoutFilter.setLocalLogout(true);
        filters.put("logout", logoutFilter);

        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.setConfig(config);
        securityFilter.setClients("CasClient");
        filters.put("security", securityFilter);

        shiroFilterFactoryBean.setFilters(filters);

        loadShiroFilterChain(shiroFilterFactoryBean, jboneConfiguration);
        return shiroFilterFactoryBean;
    }

}
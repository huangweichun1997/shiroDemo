package com.hwc.shiro_study.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @projectName: shiro_study
 * @package: com.hwc.shiro_study.config
 * @className: ShiroConfig
 * @author: huangweichun
 * @description: shiro配置类
 * @date: 2023/9/20 19:46
 * @version: 1.0
 */
@Configuration
public class ShiroConfig {

    @Value("shiroAnnoUrl")
    private String shiroAnnoUrl;


    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("shiroUserAuthorRealm") Realm realm,
                                                     CacheManager cacheManager,
                                                     RememberMeManager rememberManager,
                                                     SessionManager sessionManager) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        manager.setCacheManager(cacheManager);
        manager.setRememberMeManager(rememberManager);
        manager.setSessionManager(sessionManager);
        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactory(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean sfBean = new ShiroFilterFactoryBean();
        sfBean.setSecurityManager(securityManager);
        sfBean.setLoginUrl("/login");
        //定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
        LinkedHashMap<String, String> map =
                new LinkedHashMap<>();
        //静态资源允许匿名访问:"anon"
        String[] annoUrls = shiroAnnoUrl.split(";");
        for (String annoUrl : annoUrls) {
            map.put(annoUrl, "anno");
        }
        map.put("/logout", "logout");
//        map.put("/bower_components/**", "anon");
//        map.put("/modules/**", "anon");
//        map.put("/dist/**", "anon");
//        map.put("/plugins/**", "anon");

        //除了匿名访问的资源,其它都要认证("authc")后访问
        map.put("/**", "authc");
        sfBean.setFilterChainDefinitionMap(map);
        return sfBean;
    }


    @Bean
    public CacheManager shiroCacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    @Bean
    public RememberMeManager rememberMeManager() {
        CookieRememberMeManager cManager = new CookieRememberMeManager();
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cManager.setCookie(cookie);
        return cManager;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sManager = new DefaultWebSessionManager();
        sManager.setGlobalSessionTimeout(60 * 60 * 1000);
        return sManager;
    }

}

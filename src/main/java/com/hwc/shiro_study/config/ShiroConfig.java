package com.hwc.shiro_study.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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
    public DefaultWebSecurityManager securityManager(Realm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
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



}

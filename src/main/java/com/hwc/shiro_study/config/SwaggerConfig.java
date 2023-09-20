package com.hwc.shiro_study.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @projectName: shiro_study
 * @package: com.hwc.shiro_study.config
 * @className: SwaggerConfig
 * @author: huangweichun
 * @description: TODO
 * @date: 2023/9/20 20:53
 * @version: 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean enableSwagger;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enableSwagger)
                .host("localhost:8081")
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hwc.shiro_study.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("ShiroStudy文档")
                        .description("这是ShiroStudy项目的文档")
                        .version("1.0")
                        .contact(new Contact("Shiro学习项目","huangweichun1997.github.io","huangweichun1997@163.com"))
//                        .license("The Apache License")
//                        .licenseUrl("http://www.baidu.com")
                        .build());
    }

}

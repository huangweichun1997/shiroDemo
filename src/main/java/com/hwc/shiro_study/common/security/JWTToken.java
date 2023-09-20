package com.hwc.shiro_study.common.security;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @projectName: shiro_study
 * @package: com.hwc.shiro_study.common.security
 * @className: JWTToken
 * @author: huangweichun
 * @description: TODO
 * @date: 2023/9/20 20:14
 * @version: 1.0
 */
public class JWTToken implements AuthenticationToken {


    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    /**
     * Returns the account identity submitted during the authentication process.
     * 在验证过程中返回用户身份信息
     * @return
     */
    @Override
    public Object getPrincipal() {
        return token;
    }

    /**
     * Returns the credentials submitted by the user during the authentication process that verifies the submitted account identity.
     * 返回用户在验证提交的帐户标识的身份验证过程中提交的凭据
     * @return
     */
    @Override
    public Object getCredentials() {
        return token;
    }
}

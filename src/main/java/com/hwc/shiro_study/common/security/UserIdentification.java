package com.hwc.shiro_study.common.security;

import lombok.Data;
import org.apache.shiro.session.Session;

/**
 * @projectName: shiro_study
 * @package: com.hwc.shiro_study.common.security
 * @className: UserIdentification
 * @author: huangweichun
 * @description: 用户的请求身份信息
 * @date: 2023/9/20 20:24
 * @version: 1.0
 */

@Data
public class UserIdentification {

    /**
     * 用户token
     */
    private String token;

    /**
     * 用户session信息
     */
    private Session session;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPwd;


    public UserIdentification(String token) {
        this.token = token;
    }

    public UserIdentification(Session session) {
        this.session = session;
    }

    public UserIdentification(String token, String userName, String userPwd) {
        this.userName = userName;
        this.userPwd = userPwd;
    }
}

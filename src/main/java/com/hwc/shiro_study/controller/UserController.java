package com.hwc.shiro_study.controller;

import com.hwc.shiro_study.common.utils.JsonResult;
import com.hwc.shiro_study.dto.UserDto;
import com.hwc.shiro_study.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import java.util.List;

/**
 * @projectName: shiro_study
 * @package: com.hwc.shiro_study.controller
 * @className: UserController
 * @author: huangweichun
 * @description: 用户信息处理类
 * @date: 2023/9/20 20:59
 * @version: 1.0
 */
@Api(value = "用户接口类", tags = "用户")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "查看用户信息", tags = "信息查询")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public UserDto infoUser(@RequestParam(value = "userName") String userId,
                            HttpServletRequest request,
                            HttpServletResponse response) {

        String token = request.getHeader("X-Token");
        String token1 = request.getHeader("token");

        System.out.println(token);
        System.out.println(token1);
//        return userService.getById(userId);
        return null;
    }


    @ApiOperation(value = "获取用户列表", tags = "信息查询")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserDto> listUser(){

        return userService.list();
    }


    /**
     * 用戶登录验证
     * @param userDto
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "用户登录", tags = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult<UsernamePasswordToken> userLogin(@RequestBody UserDto userDto,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        // 1.获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        // 2.通过Subject提交用户信息,交给shiro框架进行认证操作
        // 2.1封装用户信息
        UsernamePasswordToken token = new UsernamePasswordToken(userDto.getUserName(), userDto.getUserPwd());
        // 2.2对用户信息进行身份认证
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return new JsonResult<>("500", "用户登录信息错误，请重新输入登录信息。");
        }
        //分析:
        //1)token会传给shiro的SecurityManager
        //2)SecurityManager将token传递给认证管理器
        //3)认证管理器会将token传递给realm
        return new JsonResult<UsernamePasswordToken>("200", "登录成功", token);
    }

}

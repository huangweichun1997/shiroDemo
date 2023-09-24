package com.hwc.shiro_study.controller;

import com.hwc.shiro_study.dto.UserDto;
import com.hwc.shiro_study.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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





}

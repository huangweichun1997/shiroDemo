package com.hwc.shiro_study.controller;

import com.hwc.shiro_study.dto.UserDto;
import com.hwc.shiro_study.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @projectName: shiro_study
 * @package: com.hwc.shiro_study.controller
 * @className: UserController
 * @author: huangweichun
 * @description: TODO
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
    public UserDto infoUser(@RequestParam(value = "userName") String userId) {
        return userService.getById(userId);
    }


    @ApiOperation(value = "获取用户列表", tags = "信息查询")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserDto> listUser(){

        return userService.list();
    }





}

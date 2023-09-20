package com.hwc.shiro_study.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: shiro_study
 * @package: com.hwc.shiro_study.dto
 * @className: UserDto
 * @author: huangweichun
 * @description: TODO
 * @date: 2023/9/20 21:00
 * @version: 1.0
 */
@Data
@TableName("user_info")
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String userName;

    private String userPwd;

    @TableLogic
    private Integer delted;



}

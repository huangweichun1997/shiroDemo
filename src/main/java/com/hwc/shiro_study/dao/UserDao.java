package com.hwc.shiro_study.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwc.shiro_study.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<UserDto> {
}

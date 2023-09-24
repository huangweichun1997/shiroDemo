package com.hwc.shiro_study.common.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Huang_Weichun
 * @project : shiroDemo
 * @date : 2023/9/24 22:12
 */
@Data
@NoArgsConstructor
public class JsonResult<T> {


    private String code;

    private String msg;

    private T data;


    public JsonResult(String code) {
        this.code = code;
    }

    public JsonResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}

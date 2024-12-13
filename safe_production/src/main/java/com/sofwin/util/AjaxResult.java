package com.sofwin.util;

import lombok.Data;

/**
 * @packageName: com.sofwin.util
 * @user: mitu
 * @date: 2024/11/27 11:12
 * @email  
 * @description: TODO
 */
@Data
public class AjaxResult {
    private Integer code;
    private String msg;
    private Object result;
    public AjaxResult(){

    }
    public AjaxResult(Integer code,String msg,Object result){
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
    public static AjaxResult success(String msg,Object result){
        return new AjaxResult(200,msg,result);
    }
    public static AjaxResult success(Object result){
        return new AjaxResult(200,"操作成功",result);
    }
    public static AjaxResult error(Integer code,String msg,Object result){
        return new AjaxResult(code,msg,result);
    }
}

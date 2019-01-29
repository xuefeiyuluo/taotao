package com.sparrow.taotao.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by xuefeiyuluo on 2019/1/24.
 */
public class JsonFormat {

    /**
     * 返回到前端 JOSN 的键名称,错误代码 code
     */
    public final static String ERROR_CODE = "code";

    /**
     * 返回到前端 JOSN 的键名称,错误描述 desc
     */
    public final static String ERROR_DESCRIPTION = "desc";

    /**
     * 返回到前端 JOSN 的键名称,数据 data
     */
    public final static String DATA = "data";

    /**
     * 返回到前端 JOSN 的键名称,消息 msg
     */
    public final static String MSG = "msg";


    public static JSON jsonSuccessResult(Object data){
        return jsonBaseMethod(200,"success","成功",data);
    }


    public static JSON jsonSuccessWithMsgResult(String msg, Object data){
        return jsonBaseMethod(200,"success",msg,data);
    }


    public static JSON jsonFailResult(Object data){
        return jsonBaseMethod(-1,"fail","失败",data);
    }


    public static JSON jsonFailWithMsgResult(String msg, Object data){
        return jsonBaseMethod(-1,"fail",msg,data);
    }


    private static JSON jsonBaseMethod(long code,String state,String msg, Object data){
        JSONObject json = new JSONObject();
        json.put(ERROR_CODE,code);
        json.put(ERROR_DESCRIPTION,state);
        json.put(MSG,msg);
        json.put(DATA,data);
        return json;
    }
}

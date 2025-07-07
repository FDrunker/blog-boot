package io.github.fdrunker.blog.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回信息包装类
 */
@Data
public class ResultJson implements Serializable {

    /**
     * 状态码 正确为200
     */
    private static final String SUCCESS_CODE = "200";

    /**
     * 状态码
     */
    private String code;

    /**
     * 返回提示信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private Object data;

    public static ResultJson success() {
        ResultJson resultJson = new ResultJson();
        resultJson.setCode(SUCCESS_CODE);
        return resultJson;
    }

    public static ResultJson success(String msg) {
        ResultJson resultJson = success();
        resultJson.setMsg(msg);
        return resultJson;
    }

    public static ResultJson success(Object data) {
        ResultJson resultJson = success();
        resultJson.setData(data);
        return resultJson;
    }

    public static ResultJson success(String msg, Object data) {
        ResultJson resultJson = success(msg);
        resultJson.setData(data);
        return resultJson;
    }

    public static ResultJson fail(String code, String msg) {
        ResultJson resultJson = new ResultJson();
        resultJson.setCode(code);
        resultJson.setMsg(msg);
        return resultJson;
    }
}
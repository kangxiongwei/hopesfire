package com.kxw.hopesfire.biz.exception;

/**
 * 业务异常枚举
 *
 * @author kangxiongwei
 * @date 2020-07-03 18:27
 */
public enum ServiceExceptionEnum {

    LOGIN_ERROR(403, "登录失败！"),
    AUTH_ERROR(405, "授权失败！");

    private int code;
    private String msg;

    ServiceExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}

package com.kxw.hopesfire.exception;

/**
 * 业务异常枚举
 *
 * @author kangxiongwei
 * @date 2020-07-03 18:27
 */
public enum ServiceExceptionEnum {

    LOGIN_USER_NOT_EXIST(551, "用户不存在！"),
    LOGIN_PASSWORD_ERROR(552, "用户密码错误！");

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

package com.kxw.hopesfire.model;

import lombok.Data;

/**
 * Http协议统一返回结果
 *
 * @author kangxiongwei
 * @date 2020-07-01 13:49
 */
@Data
public class HttpBaseModel {

    private int code;
    private String message;
    private Object data;

    public static HttpBaseModel buildSuccess(Object data) {
        HttpBaseModel httpBaseModel = new HttpBaseModel();
        httpBaseModel.setCode(200);
        httpBaseModel.setMessage("success");
        httpBaseModel.setData(data);
        return httpBaseModel;
    }

}

package com.kxw.hopesfire.web.model;

import com.kxw.hopesfire.biz.exception.ServiceException;
import com.kxw.hopesfire.biz.exception.ServiceExceptionEnum;
import lombok.Data;
import org.apache.shiro.authc.AccountException;


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

    public static HttpBaseModel buildFailed(AccountException exp) {
        HttpBaseModel httpBaseModel = new HttpBaseModel();
        httpBaseModel.setCode(ServiceExceptionEnum.LOGIN_ERROR.getCode());
        httpBaseModel.setMessage(exp.getMessage());
        return httpBaseModel;
    }

    public static HttpBaseModel buildFailed(ServiceException exp) {
        HttpBaseModel httpBaseModel = new HttpBaseModel();
        httpBaseModel.setCode(exp.getCode());
        httpBaseModel.setMessage(exp.getMessage());
        return httpBaseModel;
    }

}

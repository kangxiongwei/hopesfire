package com.kxw.hopesfire.exception;

/**
 * 自定义业务异常
 *
 * @author kangxiongwei
 * @date 2020-07-03 18:25
 */
public class ServiceException extends RuntimeException {

    private int code;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    private ServiceException(ServiceExceptionEnum exp) {
        super(exp.getMsg());
        this.code = exp.getCode();
    }

    public static ServiceException build(ServiceExceptionEnum exception) {
        return new ServiceException(exception);
    }

    public int getCode() {
        return code;
    }

}

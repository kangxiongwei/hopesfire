package com.kxw.hopesfire.biz.enums;

/**
 * 文章状态枚举
 *
 * @author kangxiongwei
 * @date 2020/9/14 5:29 下午
 */
public enum ArticleStatusEnum {

    //0未审核 1审核中 2已通过 3已驳回 4已发表
    UNAUDITED(0, "未审核"),
    AUDITING(1, "审核中"),
    APPROVED(2, "已通过"),
    REJECTED(3, "已驳回"),
    PUBLISHED(4, "已发布");

    private Integer code;
    private String message;

    ArticleStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

package com.kxw.hopesfire.biz.enums;

/**
 * 栏目类型枚举
 *
 * @author kangxiongwei
 * @date 2020/9/14 5:34 下午
 */
public enum BannerTypeEnum {

    //1导航栏目 2文章列表 3文章内容 4图片栏目
    DEFAULT_BANNER(0, "默认栏目"),
    NAV_BANNER(1, "导航栏目"),
    ARTICLE_TITLE(2, "文章列表"),
    ARTICLE_CONTENT(3, "文章内容"),
    IMAGE_BANNER(4, "图片栏目");

    private Integer code;
    private String message;

    BannerTypeEnum(Integer code, String message) {
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

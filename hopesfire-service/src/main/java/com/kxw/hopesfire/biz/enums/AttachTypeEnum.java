package com.kxw.hopesfire.biz.enums;

/**
 * @author kangxiongwei
 * @date 2020/8/6 6:56 下午
 */
public enum AttachTypeEnum {

    HOME_PAGE_IMAGES(1, "homePage", "首页轮播图"),
    AVATAR_IMAGES(2, "avatar", "用户头像图");

    private Integer code;
    private String attachType;
    private String attachName;

    AttachTypeEnum(Integer code, String attachType, String attachName) {
        this.code = code;
        this.attachType = attachType;
        this.attachName = attachName;
    }

    public static String getAttachType(Integer code) {
        if (code == null) {
            return "";
        }
        for (AttachTypeEnum attachType : values()) {
            if (attachType.getCode().equals(code)) {
                return attachType.getAttachType();
            }
        }
        return "";
    }

    public Integer getCode() {
        return code;
    }

    public String getAttachType() {
        return attachType;
    }

}

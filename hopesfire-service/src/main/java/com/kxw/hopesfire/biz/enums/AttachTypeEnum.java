package com.kxw.hopesfire.biz.enums;

/**
 * @author kangxiongwei
 * @date 2020/8/6 6:56 下午
 */
public enum AttachTypeEnum {

    HOME_PAGE_IMAGES(1, "home", "首页轮播图", 1366, 768),
    AVATAR_IMAGES(2, "avatar", "用户头像图", 200, 200);

    private Integer code;
    private String attachType;
    private String attachName;
    private Integer width;
    private Integer height;

    AttachTypeEnum(Integer code, String attachType, String attachName, Integer width, Integer height) {
        this.code = code;
        this.attachType = attachType;
        this.attachName = attachName;
        this.width = width;
        this.height = height;
    }

    public static AttachTypeEnum getAttachType(Integer code) {
        if (code == null) {
            return null;
        }
        for (AttachTypeEnum attachType : values()) {
            if (attachType.getCode().equals(code)) {
                return attachType;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getAttachType() {
        return attachType;
    }

    public String getAttachName() {
        return attachName;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }
}

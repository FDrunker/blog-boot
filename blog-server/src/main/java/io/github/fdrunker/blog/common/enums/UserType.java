package io.github.fdrunker.blog.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户类型枚举
 */
@Getter
@AllArgsConstructor
public enum UserType {

    /**
     * 系统用户
     */
    SYSTEM_USER("00", "系统用户"),

    /**
     * 小程序用户
     */
    APPLET_USER("01", "小程序用户");


    private final String code;

    private final String desc;
}

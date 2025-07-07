package io.github.fdrunker.blog.common.model;

import lombok.Data;

@Data
public class TokenBean {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * openId
     */
    private String openId;

    /**
     * 小程序sessionKey
     */
    private String sessionKey;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 角色信息拼接字符串
     */
    private String roleId;

}

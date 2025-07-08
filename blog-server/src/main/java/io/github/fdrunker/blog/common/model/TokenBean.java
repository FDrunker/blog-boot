package io.github.fdrunker.blog.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 登录Token信息
 */
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
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
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 权限列表
     */
    private List<String> permissionList;

}

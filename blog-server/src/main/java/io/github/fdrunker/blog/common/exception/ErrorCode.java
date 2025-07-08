package io.github.fdrunker.blog.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * 异常信息
     */
    NO_TOKEN("1001", "用户未登录"),
    TOKEN_EXPIRE("1002", "登录超时，请重新登录"),
    TOKEN_EXCHANGE("1003", "账号在其他地方登录，账号被踢出"),
    USER_LOGIN_ERROR("2001", "用户名或密码错误"),
    USER_STATUS_ERROR("2002", "用户已被停用，请联系管理员"),
    ARTICLE_CHECK_ERROR("3003", "资源不存在"),
    PERMISSION_ERROR("5002", "没有权限"),
    DISABLE_UPDATE_ERROR("9000", "演示环境，不支持增删改操作"),
    PARAM_ERROR("9001", "请求参数有误，请重试"),
    NOT_FIND_DATA("9002", "数据不存在，请刷新后重试"),
    OTHER_ERROR("9999", "未知异常");

    private final String code;
    private final String msg;

}

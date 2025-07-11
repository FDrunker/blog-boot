package io.github.fdrunker.blog.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常-Base父类，细化的自定义异常，应该继承此类
 * 统一异常处理时，会根据此异常类型做判断，返回结果时，如果是自定义异常自动解析code和errorMsg返回
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    private final String code;

    private final String errorMsg;

    public BaseException(String code, String errorMsg) {
        super(errorMsg);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public BaseException(ErrorCode code) {
        super(code.getMsg());
        this.code = code.getCode();
        this.errorMsg = code.getMsg();
    }

    public BaseException(ErrorCode code, String errorMsg) {
        super(errorMsg);
        this.code = code.getCode();
        this.errorMsg = errorMsg;
    }
}
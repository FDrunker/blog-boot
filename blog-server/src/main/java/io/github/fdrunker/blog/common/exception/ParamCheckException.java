package io.github.fdrunker.blog.common.exception;

public class ParamCheckException extends BaseException {

    public ParamCheckException(String code, String errorMsg) {
        super(code, errorMsg);
    }

    public ParamCheckException(ErrorCode code) {
        super(code);
    }

    public ParamCheckException(ErrorCode code, String errorMsg) {
        super(code, errorMsg);
    }
}
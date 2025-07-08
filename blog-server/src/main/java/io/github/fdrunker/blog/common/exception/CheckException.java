package io.github.fdrunker.blog.common.exception;

public class CheckException extends BaseException {

    public CheckException(String code, String errorMsg) {
        super(code, errorMsg);
    }

    public CheckException(ErrorCode code) {
        super(code);
    }

    public CheckException(ErrorCode code, String errorMsg) {
        super(code, errorMsg);
    }
}
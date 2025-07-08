package io.github.fdrunker.blog.common.exception;

public class PermissionException extends BaseException {
    public PermissionException(String code, String errorMsg) {
        super(code, errorMsg);
    }

    public PermissionException(ErrorCode code) {
        super(code);
    }

    public PermissionException(ErrorCode code, String errorMsg) {
        super(code, errorMsg);
    }
}

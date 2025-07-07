package io.github.fdrunker.blog.handler;

import io.github.fdrunker.blog.common.exception.BaseException;
import io.github.fdrunker.blog.common.exception.ErrorCode;
import io.github.fdrunker.blog.common.model.ResultJson;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义异常处理
     *
     * @param e 异常信息
     * @return 返回结果
     */
    @ExceptionHandler(BaseException.class)
    public ResultJson mapleBaseException(BaseException e) {
        log.error("自定义异常信息 ex={}", e.getMessage(), e);
        setResponse();
        return ResultJson.fail(e.getCode(), e.getErrorMsg());
    }

    /**
     * 参数校验异常处理
     *
     * @param e 异常信息
     * @return 返回结果
     */
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public ResultJson validException(MethodArgumentNotValidException e) {
        log.error("参数校验异常信息 ex={}", e.getMessage(), e);
        BindingResult result = e.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                stringBuilder.append(fieldError.getDefaultMessage());
            });
        }
        setResponse();
        return ResultJson.fail(ErrorCode.PARAM_ERROR.getCode(), stringBuilder.toString());
    }

    /**
     * 系统异常.
     *
     * @param e 异常信息
     * @return R
     */
    @ExceptionHandler(Exception.class)
    @Order(99)
    public ResultJson exception(Exception e) {
        log.error("系统异常信息 ex={}", e.getMessage(), e);
        // 未知异常统一抛出9999
        setResponse();
        return ResultJson.fail(ErrorCode.OTHER_ERROR.getCode(), ErrorCode.OTHER_ERROR.getMsg());
    }

    private void setResponse () {
        RequestAttributes res = RequestContextHolder.getRequestAttributes();
        if (res instanceof ServletRequestAttributes) {
            Objects.requireNonNull(
                    ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                            .getResponse()
            ).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            HttpServletResponse resp = ((ServletRequestAttributes) Objects.requireNonNull(
                    RequestContextHolder.getRequestAttributes())
            ).getResponse();
            if (resp != null) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }

}

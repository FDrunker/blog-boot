package io.github.fdrunker.blog.aop;

import io.github.fdrunker.blog.common.annotation.RequiredPermission;
import io.github.fdrunker.blog.common.exception.ErrorCode;
import io.github.fdrunker.blog.common.exception.PermissionException;
import io.github.fdrunker.blog.common.utils.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.regex.Pattern;

@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class PermissionAspect {

    @Pointcut("@annotation(io.github.fdrunker.blog.common.annotation.RequiredPermission)")
    public void checkPermission() {
    }

    @Before("checkPermission()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RequiredPermission annotation = signature.getMethod().getAnnotation(RequiredPermission.class);
        if (annotation == null) {
            return;
        }

        String value = annotation.value();
        if (StringUtils.isEmpty(value)) {
            return;
        }

        List<String> permissionList = JwtUtil.getPermissionList();
        if (CollectionUtils.isEmpty(permissionList)) {
            return;
        }

        if (permissionList.stream().anyMatch(permission ->
                Pattern.matches(permission.replace("*", ".*"), value)
        )) {
            throw new PermissionException(ErrorCode.PERMISSION_ERROR);
        }
    }

}

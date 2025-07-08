package io.github.fdrunker.blog.common.annotation;

import java.lang.annotation.*;

/**
 * 权限控制
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiredPermission {

    String value() default "";

}

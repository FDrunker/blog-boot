package io.github.fdrunker.blog.common.annotation;

import io.github.fdrunker.blog.common.enums.BusinessType;
import io.github.fdrunker.blog.common.enums.OperatorType;

import java.lang.annotation.*;

/**
 * 操作日志记录
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {

    // 0网站用户 1后台用户 2小程序 3其他
    OperatorType operateType() default OperatorType.OTHER;

    // 0查询 1新增 2修改 3删除 4其他
    BusinessType businessType() default BusinessType.SELECT;

    // 描述信息，不填会取ApiOperation的value
    String value() default "";

    // 返回保存结果是否落库，没用的大结果可以不记录，比如分页查询等等，设为false即可
    boolean saveResult() default true;
}
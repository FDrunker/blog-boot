package io.github.fdrunker.blog.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.github.fdrunker.blog.common.constant.GlobalConstant;
import io.github.fdrunker.blog.common.annotation.OperationLog;
import io.github.fdrunker.blog.common.utils.JwtUtil;
import io.github.fdrunker.blog.pojo.entity.system.OperateLogEntity;
import io.github.fdrunker.blog.mapper.OperateLogMapper;
import io.micrometer.common.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;

/**
 * 操作日志切面类
 */
@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class SystemLogAspect {

    private final OperateLogMapper operateLogMapper;

    @Pointcut("@annotation(io.github.fdrunker.blog.common.annotation.OperationLog)")
    public void systemLog() {
    }

    @Around("systemLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed;
        // 定义执行开始时间
        long startTime;
        // 定义执行结束时间
        long endTime;
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 取swagger的描述信息
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        OperationLog operationLog = method.getAnnotation(OperationLog.class);
        OperateLogEntity entity = new OperateLogEntity();

        try {
            entity.setBrowser(request.getHeader("USER-AGENT"));
            entity.setOperateUrl(request.getRequestURI());
            entity.setRequestMethod(request.getMethod());
            entity.setMethod(String.valueOf(joinPoint.getSignature()));
            entity.setCreateTime(new Date());
            entity.setOperateIp(getIpAddress(request));
            // 取JWT的登录信息，无需登录可以忽略
            if (request.getHeader(GlobalConstant.TOKEN_NAME) != null) {
                entity.setCreateId(JwtUtil.getUserId());
            }
            String operateParam = JSON.toJSONStringWithDateFormat(
                    joinPoint.getArgs(),
                    "yyyy-MM-dd HH:mm:ss",
                    SerializerFeature.WriteMapNullValue
            );
            if (operateParam.length() > GlobalConstant.MAX_TEXT_LENGTH) {
                operateParam = operateParam.substring(0, GlobalConstant.MAX_TEXT_LENGTH);
            }
            entity.setOperateParam(operateParam);

            if (apiOperation != null) {
                entity.setTitle(apiOperation.value());
            }

            if (operationLog != null) {
                entity.setBusinessType(operationLog.businessType().ordinal());
                entity.setOperateType(operationLog.operateType().ordinal());
                if (StringUtils.isNotBlank(operationLog.value())) {
                    entity.setTitle(operationLog.value());
                }
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        startTime = System.currentTimeMillis();
        try {
            proceed = joinPoint.proceed();
            endTime = System.currentTimeMillis();
            entity.setRespTime(endTime - startTime);
            entity.setStatus(1);
            // 判断是否保存返回结果，列表页可以设为false
            if (operationLog != null && operationLog.saveResult()) {
                String result = JSON.toJSONString(proceed);
                if (result.length() > GlobalConstant.MAX_TEXT_LENGTH) {
                    result = result.substring(0, GlobalConstant.MAX_TEXT_LENGTH);
                }
                entity.setJsonResult(result);
            }
        } catch (Exception e) {
            // 记录异常信息
            entity.setStatus(0);
            entity.setErrorMsg(e.toString());
            throw e;
        } finally {
            endTime = System.currentTimeMillis();
            entity.setRespTime(endTime - startTime);
            operateLogMapper.insert(entity);
        }
        return proceed;
    }

    /**
     * 获取Ip地址
     */
    private static String getIpAddress(HttpServletRequest request) {
        String xip = request.getHeader("X-Real-IP");
        String xFor = request.getHeader("X-Forwarded-For");
        String unknown = "unknown";
        if (StringUtils.isNotEmpty(xFor) && !unknown.equalsIgnoreCase(xFor)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = xFor.indexOf(",");
            if (index != -1) {
                return xFor.substring(0, index);
            } else {
                return xFor;
            }
        }
        xFor = xip;
        if (StringUtils.isNotEmpty(xFor) && !unknown.equalsIgnoreCase(xFor)) {
            return xFor;
        }
        if (StringUtils.isBlank(xFor) || unknown.equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(xFor) || unknown.equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(xFor) || unknown.equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(xFor) || unknown.equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(xFor) || unknown.equalsIgnoreCase(xFor)) {
            xFor = request.getRemoteAddr();
        }
        return xFor;
    }

}

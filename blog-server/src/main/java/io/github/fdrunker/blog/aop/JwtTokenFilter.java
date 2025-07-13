package io.github.fdrunker.blog.aop;

import com.alibaba.fastjson.JSON;
import io.github.fdrunker.blog.common.constant.GlobalConstant;
import io.github.fdrunker.blog.common.exception.ErrorCode;
import io.github.fdrunker.blog.common.model.ResultJson;
import io.github.fdrunker.blog.common.utils.JwtUtil;
import io.github.fdrunker.blog.common.utils.RedisUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 判断用户登录token
 */
@WebFilter(filterName = "jwtFilter", urlPatterns = {"/*"})
@RequiredArgsConstructor
public class JwtTokenFilter implements Filter {

    private final List<String> excludedUrlList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludedUrlList.addAll(Arrays.asList(
                "/login"
        ));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = ((HttpServletRequest) request).getRequestURI();
        if (excludedUrlList.stream().anyMatch(excludedUrl ->
                Pattern.matches(excludedUrl.replace("*", ".*"), url)
        )) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 处理跨域问题，跨域的请求首先会发一个options类型的请求
        if (httpServletRequest.getMethod().equals(HttpMethod.OPTIONS.name())) {
            chain.doFilter(request, response);
        }

        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        RedisUtil redisService = (RedisUtil) factory.getBean("redisUtil");
        String authorization = httpServletRequest.getHeader("Authorization");
        String account;
        // 判断token是否存在, 不存在代表未登录
        if (StringUtils.isEmpty(authorization)) {
            writeRsp(httpServletResponse, ErrorCode.NO_TOKEN);
            return;
        } else {
            account = JwtUtil.getAccount(authorization);
            String token = (String) redisService.get(GlobalConstant.getRedisUserKey(account));
            // 判断token是否存在, 不存在代表登录超时
            if (StringUtils.isEmpty(token)) {
                writeRsp(httpServletResponse, ErrorCode.TOKEN_EXPIRE);
                return;
            } else if (!token.equals(authorization)) {
                writeRsp(httpServletResponse, ErrorCode.TOKEN_EXCHANGE);
                return;
            }
        }

        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private void writeRsp(HttpServletResponse response, ErrorCode errorCode) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setHeader("content-type", "application/json;charset=UTF-8");
        try {
            response.getWriter().println(JSON.toJSON(ResultJson.fail(errorCode.getCode(), errorCode.getMsg())));
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}

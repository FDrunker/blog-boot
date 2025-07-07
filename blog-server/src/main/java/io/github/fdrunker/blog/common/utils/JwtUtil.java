package io.github.fdrunker.blog.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.fdrunker.blog.common.constant.GlobalConstant;
import io.github.fdrunker.blog.common.exception.ErrorCode;
import io.github.fdrunker.blog.common.exception.ParamCheckException;
import io.github.fdrunker.blog.common.model.TokenBean;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

public class JwtUtil {

    private static final String ACCOUNT = "account";
    private static final String USER_ID = "userId";
    private static final String USER_TYPE = "userType";
    private static final String ROLE_ID = "roleId";
    private static final String SESSION_KEY = "sessionKey";
    private static final String OPEN_ID = "openId";

    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token, String account) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(GlobalConstant.SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withClaim(ACCOUNT, account).build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户登录帐号
     */
    public static String getAccount() {
        try {
            DecodedJWT jwt = getJwt();
            if (jwt == null) {
                return null;
            }
            return jwt.getClaim(ACCOUNT).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户登录帐号
     */
    public static String getAccount(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(ACCOUNT).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户ID
     */
    public static Long getUserId() {
        try {
            DecodedJWT jwt = getJwt();
            if (jwt == null) {
                return null;
            }
            return jwt.getClaim(USER_ID).asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户ID
     */
    public static Long getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(USER_ID).asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取token中的信息无需secret解密也能获得
     *
     * @return token中包含的信息
     */
    public static TokenBean getTokenMsg() {
        TokenBean tokenBean = new TokenBean();
        try {
            DecodedJWT jwt = getJwt();
            if (jwt == null) {
                return tokenBean;
            }
            tokenBean.setUserId(jwt.getClaim(USER_ID).asLong());
            tokenBean.setAccount(jwt.getClaim(ACCOUNT).asString());
            return tokenBean;

        } catch (JWTDecodeException e) {
            return tokenBean;
        }
    }

    /**
     * 获取并解析JWT
     *
     * @return 解析后的JWT
     */
    private static DecodedJWT getJwt() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(servletRequestAttributes)) {
            throw new ParamCheckException(ErrorCode.PARAM_ERROR);
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            return null;
        }
        return JWT.decode(authorization);
    }

    /**
     * 获取token中的role id
     *
     * @return 角色信息
     */
    public static String getRoleList() {
        DecodedJWT jwt = getJwt();
        if (jwt == null) {
            return null;
        }
        return jwt.getClaim(ROLE_ID).asString();
    }

    /**
     * 校验token是否有效
     *
     * @param token token信息
     * @return 返回结果
     */
    public static boolean verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(GlobalConstant.SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            jwt.getClaims();
            return true;
        } catch (Exception e) {
            e.fillInStackTrace();
            return false;
        }
    }

    public static String getSessionKey() {
        try {
            DecodedJWT jwt = getJwt();
            if (jwt == null) {
                return null;
            }
            return jwt.getClaim(SESSION_KEY).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户登录帐号
     */
    public static String getOpenId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(OPEN_ID).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 创建token
     *
     * @param tokenBean token保存的信息
     * @return token
     */
    public static String createToken(TokenBean tokenBean) {
        Algorithm algorithm = Algorithm.HMAC256(GlobalConstant.SECRET);
        return JWT.create()
                .withClaim(ACCOUNT, tokenBean.getAccount())
                .withClaim(USER_ID, tokenBean.getUserId())
                .withClaim(USER_TYPE, tokenBean.getUserType())
                .withClaim(ROLE_ID, tokenBean.getRoleId())
                .withClaim(SESSION_KEY, tokenBean.getSessionKey())
                .withClaim(OPEN_ID, tokenBean.getOpenId())
                .sign(algorithm);
    }

}

package io.github.fdrunker.blog.common.constant;

public class GlobalConstant {

    private GlobalConstant() {
    }

    /**
     * 用户储存在redis中的过期时间
     */
    public static final long EXPIRE_TIME = 60 * 60 * 12L;

    /**
     * 网页端通过小程序授权结果的过期时间
     */
    public static final long APPLET_AUTH_EXPIRE_TIME = 300;

    /**
     * 生成token的私钥
     */
    public static final String SECRET = "fdrunker20001124";

    /**
     * 管理员用户登录token保存在redis的key值
     *
     * @param account 用户登录帐号
     * @return token保存在redis的key
     */
    public static String getRedisUserKey(String account) {
        return "MAPLE_BOOT_ADMIN:" + account;
    }

    /**
     * 小程序用户登录token保存在redis的key值
     *
     * @param account 用户登录帐号
     * @return token保存在redis的key
     */
    public static String getAppletRedisUserKey(String account) {
        return "MAPLE_APPLET:" + account;
    }


    /**
     * web用户登录token保存在redis的key值
     *
     * @param account 用户登录帐号
     * @return token保存在redis的key
     */
    public static String getWebRedisUserKey(Long account) {
        return "MAPLE_BOOT_WEB:" + account;
    }

    /**
     * 网页端通过小程序授权编码保存在redis的key值
     *
     * @param uniCode 唯一编码
     * @return 保存在redis的key
     */
    public static String getAppletAuthRedisKey(String uniCode) {
        return "MAPLE_APPLET_AUTH:" + uniCode;
    }

    /**
     * 网页端通过小程序授权结果保存在redis的key值
     *
     * @param uniCode 唯一编码
     * @return 保存在redis的key
     */
    public static String getAppletAuthResultRedisKey(String uniCode) {
        return "MAPLE_APPLET_AUTH_RESULT:" + uniCode;
    }

    /**
     * 前端传递token的header名称
     */
    public static final String TOKEN_NAME = "Authorization";

    /**
     * 启用
     */
    public static final Integer ENABLE = 1;

    /**
     * 禁用
     */
    public static final Integer DISABLE = 0;

    /**
     * 文本最大长度
     */
    public static final int MAX_TEXT_LENGTH = 65000;

}

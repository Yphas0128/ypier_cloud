package cn.ypier.constant;

/**
 * 权限相关常量定义
 * @Author Ypier
 */
public interface AuthConstant {
    /**
     * 后台管理client_id
     */
    String ADMIN_CLIENT_ID = "client-app";

    /**
     * 前台商城client_id
     */
    String PORTAL_CLIENT_ID = "portal-app";

    String AUTHORITY_PREFIX = "ROLE_";

    String AUTHORITY_CLAIM_NAME = "authorities";

    String JWT_TOKEN_HEADER = "Authorization";

    String JWT_TOKEN_PREFIX = "Bearer ";

    String USER_TOKEN_HEADER = "user";
}

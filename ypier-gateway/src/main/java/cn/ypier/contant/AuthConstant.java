package cn.ypier.contant;

public interface AuthConstant {
    String AUTHORITY_PREFIX = "ROLE_";

    String AUTHORITY_CLAIM_NAME = "authorities";

    String JWT_TOKEN_HEADER = "Authorization";
    String JWT_TOKEN_PREFIX = "Bearer ";
    String USER_TOKEN_HEADER = "user";
}

package cn.ypier.component;

import cn.hutool.core.util.IdUtil;
import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * @Author Ypier
 */
@Component
public class JwtTokenUtils {
    private static final String KEY = "ypier";
    //一天
    private static  Long expires_in = 1000 * 60 * 60 * 24L;
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    /**
     * 根据用户信息生成token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 生成token
     * @param claims
     * @return
     */
    public String generateToken(Map<String, Object> claims){
        // 指定签名的时候使用的签名算法
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        // 主键
        String id = IdUtil.randomUUID();
        // jwt 生成时间
        Long nowMillis  = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        SecretKey key = generalKey();
        // 这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder jwtBuilder = Jwts.builder()
                // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setId(id)
                // iat: jwt的签发时间
                .setIssuedAt(now)
                .setClaims(claims)
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(algorithm, key);
        // 设置过期时间
        if (expires_in >= 0) {
            long expMillis = nowMillis + expires_in;
            Date exp = new Date(expMillis);
            jwtBuilder.setExpiration(exp);
        }

        return jwtBuilder.compact();
    }


    private SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(KEY.getBytes());
        SecretKeySpec key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 获取 用户名称
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    /**
     * 验证
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    public Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 获取claim
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(generalKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }


}

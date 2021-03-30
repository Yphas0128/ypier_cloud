package cn.ypier.authorization;

/**
 * 鉴权管理器
 * @Author Ypier
 */

import cn.hutool.core.convert.Convert;
import cn.ypier.config.IgnoreUrlsConfig;
import cn.ypier.contant.AuthConstant;
import cn.ypier.contant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorization) {
        PathMatcher pathMatcher   = new AntPathMatcher();
        ServerHttpRequest request = authorization.getExchange().getRequest();
        URI uri = request.getURI();
        // 通信白名单 直接放行
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }
        // 跨域问题
        if (request.getMethod().equals(HttpMethod.OPTIONS)){
            return Mono.just(new AuthorizationDecision(true));
        }

        // 不同体系 不允许互相访问
//        try {
//            String token = request.getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
//
//            if(StrUtil.isEmpty(token)){
//                return Mono.just(new AuthorizationDecision(false));
//            }
//
//            String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
//            JWSObject jwsObject = JWSObject.parse(realToken);
//            String userStr = jwsObject.getPayload().toString();
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        // 根据当前uri 获取不 同的 角色
        Object object = redisTemplate.opsForHash().get(RedisConstant.RESOURCE_ROLES_MAP, uri.getPath());

        List<String> authorities = Convert.toList(String.class, object);

        authorities = authorities.stream().map(u -> u = AuthConstant.AUTHORITY_PREFIX + u).collect(Collectors.toList());
        //认证通过且角色匹配的用户可访问当前路径
        return mono.filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                //  roleId是请求用户的角色(格式:ROLE_{roleId})
                //  authorities是请求资源所需要角色的集合
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}

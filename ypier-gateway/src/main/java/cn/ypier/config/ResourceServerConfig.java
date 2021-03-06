package cn.ypier.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.ypier.authorization.AuthorizationManager;
import cn.ypier.commponent.RestAuthenticationEntryPoint;
import cn.ypier.commponent.RestfulAccessDeniedHandler;
import cn.ypier.contant.AuthConstant;
import cn.ypier.filter.IgnoreUrlsRemoveJwtFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Mono;

/*
 * @Author Ypier
 */
@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

    private AuthorizationManager authorizationManager;
    private IgnoreUrlsConfig ignoreUrlsConfig;
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter;
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // "ROLE_"
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstant.AUTHORITY_PREFIX);
        // "authorities"
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstant.AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http){
        http.oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
        // ???????????????JWT???????????????????????????????????????
        http.oauth2ResourceServer().authenticationEntryPoint(restAuthenticationEntryPoint);
        // ??????????????? ????????????JWT?????????
        http.addFilterBefore(ignoreUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        http.authorizeExchange()
                // list -> string Convert.toStr(ignoreUrlsConfig.getUrls())
                .pathMatchers(ArrayUtil.toArray(ignoreUrlsConfig.getUrls(),String.class))
                .permitAll()
                .anyExchange()
                // ?????????????????????
                .access(authorizationManager)
                .and()
                // ????????????
                .exceptionHandling()
                // ???????????????
                .accessDeniedHandler(restfulAccessDeniedHandler)
                // ???????????????
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and().csrf().disable();

        return  http.build();
    }
}

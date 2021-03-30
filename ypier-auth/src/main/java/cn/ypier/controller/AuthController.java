package cn.ypier.controller;

import cn.ypier.constant.AuthConstant;
import cn.ypier.domain.Oauth2TokenDto;
import cn.ypier.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/** 登录验证
 * @Author Ypier
 */
@RestController
@RequestMapping(value = "/oauth")
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @PostMapping(value = "/token")
    public CommonResult postAccessToken(Principal principal,  @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {

        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_HEADER).build();

        return CommonResult.success(oauth2TokenDto);
    }
}

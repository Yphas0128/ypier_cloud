package cn.ypier.service;

import cn.ypier.result.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "ypier-auth",path = "/oauth")
public interface AuthService {

    /**
     * oauth 模块 访问 获取 token
     * @param parameters
     * @return
     */
    @PostMapping(value = "/token")
    CommonResult getAccessToken(@RequestParam Map<String, String> parameters);
}

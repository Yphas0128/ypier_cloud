package cn.ypier.client;

import cn.ypier.pojo.SysMenu;
import cn.ypier.result.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ypier-system",path = "/menu")
public interface MenuClient {

    @PostMapping(value = "/test")
    CommonResult menu_test(@RequestBody SysMenu menu);
}

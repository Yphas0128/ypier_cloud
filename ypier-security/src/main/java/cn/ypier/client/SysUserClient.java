package cn.ypier.client;
/*
 * @Author Ypier
 */

import cn.ypier.pojo.SysPermission;
import cn.ypier.pojo.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户模块
 * @Author Ypier
 */
@FeignClient(name = "ypier-system",path = "/user")
public interface SysUserClient {

    @GetMapping(value = "/selectUser")
    List<SysUser> selectUser();

    @GetMapping(value = "/selectByUsername")
    SysUser selectByUsername(@RequestParam("username") String username);

    @GetMapping(value = "/selectPermissionList/{id}")
    List<SysPermission> selectPermissionList(@PathVariable("id") Integer id);

    @GetMapping(value = "/loadUserByUsername")
    SysUser loadUserByUsername(@RequestParam("username") String username);
}
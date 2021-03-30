package cn.ypier.controller;

import cn.ypier.pojo.SysPermission;
import cn.ypier.pojo.SysUser;
import cn.ypier.pojo.SysUserRole;
import cn.ypier.result.CommonResult;
import cn.ypier.service.SysUserService;
import cn.ypier.verify.LoginVerify;
import cn.ypier.verify.UserAddVerify;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Ypier
 */

@RestController
@RequestMapping(value = "/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户登录
     * @return
     */
    @PostMapping(value = "/login")
    public CommonResult login(@Valid LoginVerify loginVerify, BindingResult bind){
        if(bind != null && bind.hasErrors()){
            return  CommonResult.failed(bind.getFieldError().getDefaultMessage());
        }
        return  sysUserService.login(loginVerify.getUsername(),loginVerify.getPassword());
    }

    /**
     * 页面 分页
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/list")
    public CommonResult list(@RequestParam(value = "keyword",required = false) String keyword,
                             @RequestParam(value = "pageNum",defaultValue = "1") long pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "5") long pageSize){
        Page<SysUser> userList = sysUserService.selectList(keyword,pageNum,pageSize);
        return  CommonResult.success(userList);
    }


    /**
     * 添加 用户
     * @return
     */
    @PostMapping(value = "/add")
    public CommonResult add(@Valid UserAddVerify user, BindingResult bind){
        if(bind != null && bind.hasErrors()){
            return CommonResult.failed(bind.getFieldError().getDefaultMessage());
        }
        // 用户名不可 存在 相同
        List<SysUser> sysUserList = sysUserService.selectByname(user.getUsername());
        if(sysUserList.size()>0){
            return CommonResult.failed("用户名已存在");
        }

        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(user, sysUser);
        sysUserService.add(sysUser);
        return CommonResult.success();
    }


    /**
     * 分配 角色
     * @return
     */
    @PostMapping(value = "/allocRoleUser/{uId}")
    public CommonResult allocRoleUser(@PathVariable("uId") Integer uId,@RequestParam List<Integer> roleIds){
        sysUserService.allocRoleUser(uId,roleIds);
        return CommonResult.success();
    }


    /**
     * 根据
     * @return
     */
    @GetMapping(value = "/roleListUser/{id}")
    public CommonResult roleListUser(@PathVariable("id") Integer id){
        List<SysUserRole> userRoleList =  sysUserService.roleListUser(id);
        List<Integer> ids = userRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        return CommonResult.success(ids);
    }


    @GetMapping(value = "/selectUser")
    public List<SysUser> selectUser(){
        List<SysUser> users = sysUserService.selectSysuser();
        return users;
    }

    @GetMapping(value = "/selectByUsername")
    public SysUser selectByUsername(@RequestParam String username){
        return  sysUserService.selectByUsername(username);
    }

    @GetMapping(value = "/selectPermissionList/{id}")
    public List<SysPermission> selectPermissionList(@PathVariable("id") Integer id){
        return  sysUserService.selectPermissionList(id);
    }

    @GetMapping(value = "/loadUserByUsername")
    SysUser loadUserByUsername(@RequestParam String username){
        SysUser user = sysUserService.loadUserByUsername(username);
        return user;
    }



}

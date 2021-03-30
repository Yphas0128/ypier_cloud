package cn.ypier.controller;

import cn.hutool.core.util.StrUtil;
import cn.ypier.result.CommonResult;
import cn.ypier.service.SysUserService;
import cn.ypier.verify.LoginVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/*
 * @Author Ypier
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping(value = "/login")
    public CommonResult login(@RequestBody @Valid LoginVerify loginVerify, BindingResult bind){
        if(bind !=null && bind.hasErrors()){
            return CommonResult.loginfail(bind.getFieldError().getDefaultMessage());
        }
        String token = sysUserService.login(loginVerify.getUsername(),loginVerify.getPassword());
        if(!StrUtil.isEmptyIfStr(token)){
            Map<String,Object> res = new HashMap<>();
            res.put("token",token);
            return  CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    @GetMapping(value = "/query1")
    @PreAuthorize("hasAnyAuthority('/user/query1')")
    public CommonResult query1(){
        return  CommonResult.success();
    }

    @GetMapping(value = "/query")
    @PreAuthorize("hasAnyAuthority('/user/query')")
    public CommonResult query(){
        return  CommonResult.success();
    }
}

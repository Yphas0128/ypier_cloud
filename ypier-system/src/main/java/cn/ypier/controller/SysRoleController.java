package cn.ypier.controller;

import cn.ypier.pojo.SysMenu;
import cn.ypier.pojo.SysRole;
import cn.ypier.result.CommonResult;
import cn.ypier.service.SysRoleService;
import cn.ypier.verify.RoleVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author Ypier
 */
@RestController
@RequestMapping(value = "/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 角色 首页
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping(value = "/list")
    public CommonResult list(@RequestParam(value = "keyword", required = false) String keyword,
                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
            List<SysRole> roleList = sysRoleService.listRole(keyword, pageSize, pageNum);
        return CommonResult.success(roleList);
    }

    /**
     * 添加 角色
     * @param role
     * @param bind
     * @return
     */
    @PostMapping(value = "/add")
    public CommonResult add(@Valid RoleVerify role, BindingResult bind){
        if(bind != null && bind.hasErrors()){
            return  CommonResult.failed(bind.getFieldError().getDefaultMessage());
        }
        SysRole sysRole = new SysRole();
        sysRole.setName(role.getName());
        sysRole.setDescription(role.getDescription());
        sysRole.setStatus(role.getStatus());
        int count =  sysRoleService.add(sysRole);
        if(count > 0 ){
            return  CommonResult.success();
        }else{
            return  CommonResult.failed();
        }
    }


    /**
     * 角色分配菜单
     * @return
     */
    @PostMapping(value = "/alloc_menu")
    public CommonResult alloc_menu(Integer roleId,@RequestParam List<Integer> menuIds){
        sysRoleService.allocMenu(roleId,menuIds);
        return CommonResult.success();
    }


    /**
     * 角色 选中
     * @param id
     * @return
     */
    @GetMapping(value = "/role_menu/{id}")
    public CommonResult role_menu(@PathVariable("id") Integer id){
        List<Integer> ids = sysRoleService.getRoleMenu(id);
        return CommonResult.success(ids);
    }

    /**
     * @return
     */
    @GetMapping(value = "/role_list")
    public CommonResult role_list(){
        List<SysRole> roleList = sysRoleService.getAllRole();
        return  CommonResult.success(roleList);
    }
}

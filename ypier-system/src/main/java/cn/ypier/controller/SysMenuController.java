package cn.ypier.controller;
/**
 * @Author Ypier
 */

import cn.ypier.dto.SysMenuNode;
import cn.ypier.pojo.SysMenu;
import cn.ypier.result.CommonResult;
import cn.ypier.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @PostMapping(value = "/test")
    public CommonResult test(@RequestBody SysMenu menu){
        int i = sysMenuService.insertMenu(menu);
        return CommonResult.success(i);
    }

    /**
     * 菜单 树状图
     * @return
     */
    @GetMapping(value = "/tree_list")
    public CommonResult tree_list(){
        List<SysMenuNode> nodeList = sysMenuService.treeList();
        return CommonResult.success(nodeList);
    }


    /**
     * 添加菜单
     * @param sysMenu
     * @param bind
     * @return
     */
    @PostMapping(value = "/add")
    public CommonResult add(@Valid SysMenu sysMenu, BindingResult bind){
        if(bind !=null && bind.hasErrors()){
            return  CommonResult.failed(bind.getFieldError().getDefaultMessage());
        }

        int count = sysMenuService.insertMenu(sysMenu);
        if(count > 0){
            return CommonResult.success();
        }else{
            return  CommonResult.failed();
        }
    }

    /**
     * 获取
     * @param parentId
     * @return
     */
    @GetMapping(value = "/list/{parentId}")
    public CommonResult selectMenus(@PathVariable("parentId") Integer parentId){
        List<SysMenu> menuList = sysMenuService.selectMenus(parentId);
        return CommonResult.success(menuList);
    }


}

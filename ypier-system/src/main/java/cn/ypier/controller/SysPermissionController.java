package cn.ypier.controller;
/**
 * @Author Ypier
 */

import cn.ypier.pojo.SysPermission;
import cn.ypier.result.CommonResult;
import cn.ypier.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/permission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 页面分页
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping(value = "/list")
    public CommonResult list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        Page<SysPermission> page = sysPermissionService.list(keyword, pageNum, pageSize);
        return CommonResult.success(page);
    }


}

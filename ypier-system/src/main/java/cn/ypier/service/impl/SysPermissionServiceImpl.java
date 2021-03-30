package cn.ypier.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.ypier.constant.RedisConstant;
import cn.ypier.dto.PermissionRoleDto;
import cn.ypier.dto.UrlRoleDto;
import cn.ypier.mapper.SysPermissionMapper;
import cn.ypier.pojo.SysPermission;
import cn.ypier.pojo.SysRole;
import cn.ypier.service.RedisService;
import cn.ypier.service.SysPermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author Ypier
 */

@Component
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private RedisService redisService;
    @Override
    public Page<SysPermission> list(String keyword, Integer pageNum, Integer pageSize) {
        QueryWrapper<SysPermission> wrapper = new QueryWrapper<>();
        Page<SysPermission> page = new Page<>(pageNum, pageSize);
        Page<SysPermission> permissionPage = sysPermissionMapper.selectPage(page, wrapper);
        Integer total = sysPermissionMapper.selectCount(wrapper);
        permissionPage.setTotal(total);
        return permissionPage;
    }


    @Override
    public List<PermissionRoleDto> permissionListRole() {
        List<PermissionRoleDto> permissionList = sysPermissionMapper.permissionListRole();
        Map<String,List<String>> map = new HashMap<>();
        // 是否可以封装成一个对象
        permissionList.forEach(permission->{
            permissionToRole(permission,map);
        });
        // 设置
        redisService.hSetAll(RedisConstant.RESOURCE_ROLES_MAP,map);
        return  permissionList;
    }

    private  void permissionToRole(PermissionRoleDto permission, Map<String,List<String>> map) {
        List<String> roleList = permission.getRoles().stream().map(SysRole::getName).collect(Collectors.toList());
        map.put(permission.getUrl(),roleList);
    }
}

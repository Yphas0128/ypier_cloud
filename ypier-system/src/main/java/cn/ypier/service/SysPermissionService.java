package cn.ypier.service;

import cn.ypier.dto.PermissionRoleDto;
import cn.ypier.pojo.SysPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface SysPermissionService {

    Page<SysPermission> list(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 不同的url 属于 不同的角色
     */
    List<PermissionRoleDto> permissionListRole();
}

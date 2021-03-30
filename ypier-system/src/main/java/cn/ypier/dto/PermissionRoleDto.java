package cn.ypier.dto;
/*
 * @Author Ypier
 */

import cn.ypier.pojo.SysPermission;
import cn.ypier.pojo.SysRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PermissionRoleDto extends SysPermission {

    @Getter
    @Setter
    private List<SysRole> roles;
}

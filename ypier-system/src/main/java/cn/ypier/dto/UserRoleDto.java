package cn.ypier.dto;

import cn.ypier.pojo.SysRole;
import cn.ypier.pojo.SysUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author Ypier
 */
public class UserRoleDto extends SysUser {

    @Getter
    @Setter
    private List<SysRole> roles;
}

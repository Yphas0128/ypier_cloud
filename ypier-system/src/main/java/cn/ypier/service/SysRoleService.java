package cn.ypier.service;
/*
 * @Author Ypier
 */

import cn.ypier.pojo.SysRole;

import java.util.List;

public interface SysRoleService {
    int add(SysRole sysRole);

    List<SysRole> listRole(String keyword, Integer pageSize, Integer pageNum);

    void allocMenu(Integer roleId, List<Integer> menuIds);

    List<Integer> getRoleMenu(Integer id);

    List<SysRole> getAllRole();
}

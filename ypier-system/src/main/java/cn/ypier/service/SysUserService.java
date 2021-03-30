package cn.ypier.service;

import cn.ypier.pojo.SysPermission;
import cn.ypier.pojo.SysUser;
import cn.ypier.pojo.SysUserRole;
import cn.ypier.result.CommonResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface SysUserService {
    /**
     * 查找全部
     */
    List<SysUser> selectSysuser();

    /**
     * 通过姓名查找
     */
    SysUser selectByUsername(String username);

    /**
     * 查询权限
     */
    List<SysPermission> selectPermissionList(Integer id);

    /**
     * 根据username 查询 相关的
     */
    SysUser loadUserByUsername(String username);

    /**
     * 分页查询用户
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<SysUser> selectList(String keyword, long pageNum, long pageSize);

    /**
     *
     * @param sysUser
     */
    SysUser add(SysUser sysUser);

    List<SysUser> selectByname(String username);

    void allocRoleUser(Integer uId, List<Integer> roleIds);

    List<SysUserRole> roleListUser(Integer id);

    CommonResult login(String username, String password);
}

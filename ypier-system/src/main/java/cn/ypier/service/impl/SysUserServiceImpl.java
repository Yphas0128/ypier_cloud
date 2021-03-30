package cn.ypier.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import cn.ypier.constant.AuthConstant;
import cn.ypier.dto.UserRoleDto;
import cn.ypier.mapper.SysPermissionMapper;
import cn.ypier.mapper.SysUserRoleMapper;
import cn.ypier.pojo.SysPermission;
import cn.ypier.pojo.SysRole;
import cn.ypier.pojo.SysUser;
import cn.ypier.mapper.SysUserMapper;
import cn.ypier.pojo.SysUserRole;
import cn.ypier.result.CommonResult;
import cn.ypier.service.AuthService;
import cn.ypier.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang.StringUtils;
import org.apache.http.conn.BasicEofSensorWatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author Ypier
 */
@Component
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private AuthService authService;

    @Override
    public CommonResult login(String username, String password) {
        Map<String,String> params = new HashMap<>();
        params.put("client_id", AuthConstant.ADMIN_CLIENT_ID);
        params.put("client_secret","123456");
        params.put("grant_type","password");
        params.put("username",username);
        params.put("password",password);
        return authService.getAccessToken(params);
    }

    /**
     * @return
     */
    @Override
    public List<SysUser> selectSysuser() {

        return sysUserMapper.selectSysuser();
    }

    @Override
    public SysUser selectByUsername(String username) {
        return sysUserMapper.selectByUsername(username);
    }

    @Override
    public List<SysPermission> selectPermissionList(Integer id) {
        return sysPermissionMapper.selectPermissionList(id);
    }


    @Override
    public SysUser loadUserByUsername(String username) {
        UserRoleDto user = sysUserMapper.loadUserByUsername(username);

        List<SysRole> roleList = user.getRoles();
        if(roleList != null){
            List<String> strList= roleList.stream().map(SysRole::getName).collect(Collectors.toList());
            user.setRoleStrs(strList);
        }
        return user;
    }


    @Override
    public  Page<SysUser> selectList(String keyword, long pageNum, long pageSize) {
        if(keyword !=null && StringUtils.isEmpty(keyword)){

        }else{
            Page<SysUser> page = new Page<>(pageNum,pageSize);
            Page<SysUser> sysUserPage = sysUserMapper.selectPage(page, null);
            Integer total = sysUserMapper.selectCount(null);
            sysUserPage.setTotal(total);
            return  sysUserPage;
        }
        return null;
    }


    @Override
    public SysUser add(SysUser sysUser) {
        sysUser.setEnabled(1);
        String encodePassword = BCrypt.hashpw(sysUser.getPassword());
        sysUser.setPassword(encodePassword);
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        // 添加 用户
        sysUserMapper.insert(sysUser);
        return sysUser;
    }

    /**
     * @param username
     * @return
     */
    @Override
    public List<SysUser> selectByname(String username) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        List<SysUser> users = sysUserMapper.selectByMap(map);
        return  users;
    }

    /**
     * @param uId
     * @param roleIds
     */
    @Override
    public void allocRoleUser(Integer uId, List<Integer> roleIds) {
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",uId);
        // 删除
        sysUserRoleMapper.delete(wrapper);
        roleIds.forEach(rId ->{
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(rId);
            sysUserRole.setUserId(uId);
            sysUserRoleMapper.insert(sysUserRole);
        });
    }


    @Override
    public List<SysUserRole> roleListUser(Integer id) {
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(wrapper);
        return sysUserRoles;
    }


}

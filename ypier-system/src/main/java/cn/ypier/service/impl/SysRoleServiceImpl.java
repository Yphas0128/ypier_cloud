package cn.ypier.service.impl;
/*
 * @Author Ypier
 */

import cn.ypier.mapper.SysRoleMapper;
import cn.ypier.mapper.SysRoleMenuMapper;
import cn.ypier.pojo.SysRole;
import cn.ypier.pojo.SysRoleMenu;
import cn.ypier.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public int add(SysRole sysRole) {
        int res = sysRoleMapper.insert(sysRole);
        return res;
    }

    @Override
    public List<SysRole> listRole(String keyword, Integer pageSize, Integer pageNum) {
        List<SysRole> roleList =  sysRoleMapper.listRole((pageNum-1)*pageSize,pageSize);
        return roleList;
    }


    @Override
    public void allocMenu(Integer roleId, List<Integer> menuIds) {
        // 根据role id 删除
        Map<String,Object> map = new HashMap<>();
        map.put("role_id",roleId);
        sysRoleMenuMapper.deleteByMap(map);

        menuIds.forEach(mId->{
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(mId);
            sysRoleMenuMapper.insert(roleMenu);
        });
    }

    @Override
    public List<Integer> getRoleMenu(Integer id) {
        Map<String,Object> map = new HashMap<>();
        map.put("role_id",id);
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectByMap(map);
        List<Integer> res = sysRoleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        return res;
    }

    @Override
    public List<SysRole> getAllRole() {
        return  sysRoleMapper.selectByMap(null);
    }
}

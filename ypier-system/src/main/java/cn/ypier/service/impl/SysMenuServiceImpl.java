package cn.ypier.service.impl;/*
 * @Author Ypier
 */

import cn.ypier.dto.SysMenuNode;
import cn.ypier.mapper.SysMenuMapper;
import cn.ypier.pojo.SysMenu;
import cn.ypier.service.SysMenuService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    @GlobalTransactional(name = "insert-menu", rollbackFor = {Exception.class})
    public int insertMenu(SysMenu sysMenu) {
        int count = sysMenuMapper.insertMenu(sysMenu);
        return count;
    }


    @Override
    public List<SysMenu> selectMenus(Integer parentId) {
        List<SysMenu> sysMenus =  sysMenuMapper.selectMenus(parentId);
        return sysMenus;
    }

    @Override
    public List<SysMenuNode> treeList() {
        List<SysMenu> menuList = sysMenuMapper.selectAll();

        List<SysMenuNode> nodes = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0))
                .map(menu -> covertMenuNode(menu, menuList))
                .collect(Collectors.toList());

        return nodes;
    }

    private SysMenuNode covertMenuNode(SysMenu menu, List<SysMenu> menuList) {
        SysMenuNode node = new SysMenuNode();
        BeanUtils.copyProperties(menu, node);
        List<SysMenuNode> children = menuList.stream()
                .filter(submenu -> submenu.getParentId().equals(menu.getId()))
                .map(submenu -> covertMenuNode(submenu, menuList))
                .collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }


}

package cn.ypier.service;

import cn.ypier.dto.SysMenuNode;
import cn.ypier.pojo.SysMenu;

import java.util.List;

public interface SysMenuService {
    /**
     * 添加菜单
     * @param sysMenu
     * @return
     */
    int insertMenu(SysMenu sysMenu);

    /**
     * @param parentId
     * @return
     */
    List<SysMenu> selectMenus(Integer parentId);

    /**
     * @return
     */
    List<SysMenuNode> treeList();
}

package cn.ypier.dto;
/*
 * @Author Ypier
 */

import cn.ypier.pojo.SysMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SysMenuNode extends SysMenu {
    @Getter
    @Setter
    List<SysMenuNode> children;
}

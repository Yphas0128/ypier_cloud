package cn.ypier.mapper;

import cn.ypier.pojo.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    int insertMenu(SysMenu sysMenu);

    List<SysMenu> selectMenus(Integer parentId);

    List<SysMenu> selectAll();
}

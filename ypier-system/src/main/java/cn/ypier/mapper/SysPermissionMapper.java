package cn.ypier.mapper;

import cn.ypier.dto.PermissionRoleDto;
import cn.ypier.pojo.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    List<SysPermission> selectPermissionList(Integer id);

    List<PermissionRoleDto> permissionListRole();
}

package cn.ypier.mapper;

import cn.ypier.dto.UserRoleDto;
import cn.ypier.pojo.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> selectSysuser();

    SysUser selectByUsername(String username);
    
    UserRoleDto loadUserByUsername(String username);
}

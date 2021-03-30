package cn.ypier.mapper;

import cn.ypier.pojo.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jnr.ffi.annotations.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
    int insertRole(SysRole sysRole);

    List<SysRole> listRole(@Param("page") Integer apge,@Param("size") Integer size);
}

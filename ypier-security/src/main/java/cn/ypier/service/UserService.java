package cn.ypier.service;
/*
 * @Author Ypier
 */
import cn.ypier.client.SysUserClient;
import cn.ypier.pojo.SysPermission;
import cn.ypier.pojo.SysUser;
import cn.ypier.contant.ExceptionContant;
import cn.ypier.domain.AdminUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private SysUserClient sysUserClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user =  sysUserClient.selectByUsername(username);
        if(user != null ){
            List<SysPermission> permissionList  = sysUserClient.selectPermissionList(user.getId());
            return new AdminUserDetails(user,permissionList);
        }
        throw new UsernameNotFoundException(ExceptionContant.USERNAMENOTFOUND);
    }
}

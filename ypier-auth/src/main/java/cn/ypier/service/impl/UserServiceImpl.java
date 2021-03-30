package cn.ypier.service.impl;

import cn.ypier.client.SysUserClient;
import cn.ypier.constant.AuthConstant;
import cn.ypier.constant.MessageConstant;
import cn.ypier.domain.SecurityUser;
import cn.ypier.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Ypier
 */
@Component
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserClient sysUserClient;

    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        SysUser sysUser = null;
        if(AuthConstant.ADMIN_CLIENT_ID.equals(clientId)){
             // 用户
            sysUser = sysUserClient.loadUserByUsername(username);
        }else if (AuthConstant.PORTAL_CLIENT_ID.equals(clientId)){
            //

        }
        if(sysUser == null){
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }

        SecurityUser securityUser = new SecurityUser(sysUser);
        securityUser.setClientId(clientId);
        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }


}

package cn.ypier.domain;
/*
 * @Author Ypier
 */
import cn.ypier.pojo.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class SecurityUser implements UserDetails {

    private Integer id;

    private String username;

    private String password;

    private Boolean enabled;

    private String clientId;

    /**
     * 权限数据
     */
    private Collection<SimpleGrantedAuthority> authorities;

    public SecurityUser(SysUser user) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setEnabled(user.getEnabled() == 1);
        if(user.getRoleStrs() != null){
            authorities = new ArrayList<>();
            user.getRoleStrs().forEach(item->{
                authorities.add(new SimpleGrantedAuthority(item)); // 添加不同角色？
            });
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}

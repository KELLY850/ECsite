package com.kadaisite.ECsite.Admin.Auth;

import com.kadaisite.ECsite.Admin.Entity.Admin_users;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
public class AdminUser implements UserDetails {
//エンティティと結びつける為に、記述。
    private final Admin_users adminUsers;
//    権限
    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getUsername(){
//        ここでDBのメールアドレスを取得。
        return adminUsers.getEmail();
    }

    @Override
    public String getPassword(){
        return adminUsers.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }

    public Long id(){
        return adminUsers.getId();
    }
//アドミンエンティティにデータを返す。
    public Admin_users getAdminUsers() {
        return adminUsers;
    }

}

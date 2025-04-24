package com.kadaisite.ECsite.User.Auth;


import com.kadaisite.ECsite.User.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
public class LoginUser implements UserDetails {
    private final User user;
    private final Collection<? extends GrantedAuthority>authorities;
    @Override
    public String getUsername(){
        return user.getEmail();
    }
    @Override
    public String getPassword(){
        return user.getPassword();
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
        return true;
    }
    public Long getId(){
        return user.getId();
    }
//    細かに分けていたけど、ここでいっぺんにUserエンティティのデータを返す。
    public User getUser(){
        return user;
    }

}

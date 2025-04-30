package com.kadaisite.ECsite.Admin.Auth;

import com.kadaisite.ECsite.Admin.Entity.Admin_users;
import com.kadaisite.ECsite.Admin.Repository.AdminMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@Service
public class AdminUserDetailsServiceImpl implements UserDetailsService {
    private final AdminMapper adminMapper;//ここでメール検索ができるようにadminMapper使用を宣言
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        権限の入れ物準備。
        String role;
        //adminmapperでメール検索。取得データの型はユーザ情報が入っているエンティティの型にする。
        Admin_users admin = adminMapper.selectByUserEmail(email);
        if(admin != null){
            role = admin.getRole();
            Collection<? extends GrantedAuthority> auth = getAuthorities(role);
            return new AdminUser(admin,auth);
        }
        throw new UsernameNotFoundException("ユーザー情報が見つかりませんでした。");
    }
//    ユーザーが持っている権限を取得
    private Collection<? extends GrantedAuthority> getAuthorities(String role){
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}

package com.kadaisite.ECsite.User.Auth;

import com.kadaisite.ECsite.User.Entity.User;
import com.kadaisite.ECsite.User.Repository.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class LoginUserDetailsServiceImpl implements UserDetailsService {
    private  final UserMapper userMapper;
    @Override//下記emailで検索するように引数email
    public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException {
//        メールアドレスで合致するユーザーを取得。
        User user = userMapper.selectByUserEmail(email);
        if (user != null ) {
        // 対象データが存在した場合、UserDetailsの実装クラスを返す
            return new LoginUser(user,
                    Collections.emptyList());//今回はユーザーに権限がないので空を返す。
        } else {
        // 対象データが存在しない
            throw new UsernameNotFoundException(
                    email + " => 合致するアドレスが存在しません");
        }
    }

}



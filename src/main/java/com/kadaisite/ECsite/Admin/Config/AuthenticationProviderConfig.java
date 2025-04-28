package com.kadaisite.ECsite.Admin.Config;

import com.kadaisite.ECsite.Admin.Auth.AdminUserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

//ログイン認証時のパスワード・メールを比較
//@Configuration
//@AllArgsConstructor
//public class AuthenticationProviderConfig {
//    private final AdminUserDetailsServiceImpl adminUserDetailsService;
//    private final PasswordEncoder passwordEncoder;
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(adminUserDetailsService);//DBからメール情報の照合を呼び出す。
//        provider.setPasswordEncoder(passwordEncoder);//パスワードのハッシュ値が一緒か確認
//        return provider;
//    }
//}

package com.kadaisite.ECsite.Config.Auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//springsecurityの設定を有効化・カスタマイズ設定する
@Configuration
@EnableWebSecurity
public class SecurityConfig {
//    セキュリティ設定
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http
                .authorizeHttpRequests(auth->auth//下記順番を間違えたら見えなくなるので注意。
                        .requestMatchers("/css/**","/images/**").permitAll()//静的リソースの表示許可
                        .requestMatchers("/login","/").permitAll()//URLベースでの記載
                        .anyRequest().authenticated()//それ以外のページは認証必須
                )
//                .formLogin(form->form
//                        .loginPage("/login") //ログイン用URLの指定
//                        .defaultSuccessUrl("/")//ログイン成功時の遷移URL
//                )
        ;
        return http.build();
    }

}

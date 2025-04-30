package com.kadaisite.ECsite.User.Config;

import com.kadaisite.ECsite.User.Auth.LoginUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//springsecurityの設定を有効化・カスタマイズ設定する
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoginUserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    //    セキュリティ設定
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/**")//通常のECサイトページは先リンクで対応
                .authorizeHttpRequests(auth -> auth//下記順番を間違えたら見えなくなるので注意。
                        .requestMatchers("/css/**", "/images/**").permitAll()//静的リソースの表示許可
                        .requestMatchers("/login", "/").permitAll()//URLベースでの記載
                        .anyRequest().authenticated()//それ以外のページは認証必須
                )
                .formLogin(form -> form
                                .loginPage("/login") //ログイン用URLの指定
                                .loginProcessingUrl("/login") // POST処理も同じに
                                .failureUrl("/login?error")
                                .usernameParameter("email")//usernameの値を"email"から取得するよう設定する
                                .passwordParameter("password")
//                      ※　 usernameParameter(), passwordParameter()で指定する文字列は、htmlのname属性の名前と同名になる。
                                .defaultSuccessUrl("/")//ログイン成功時の遷移URL
                                .permitAll()
                );
        return http.build();
    }

}

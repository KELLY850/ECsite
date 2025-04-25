package com.kadaisite.ECsite.Admin.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
* 管理者用ログイン認証設定
* */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class AdminSecurityConfig {
    private final PasswordEncoder passwordEncoder;

    public SecurityFilterChain adminSecurityConfig(HttpSecurity http)throws Exception{
        http
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/css/**", "/images/**").permitAll()
                        .requestMatchers("/admin/login", "/admin").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form->form
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/admin/login")
                        .failureUrl("/admin/login?error")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/admin")
                        .permitAll()
                );
        return http.build();
    }
}

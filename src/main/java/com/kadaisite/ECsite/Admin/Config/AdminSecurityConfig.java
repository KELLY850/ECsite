package com.kadaisite.ECsite.Admin.Config;

import com.kadaisite.ECsite.Admin.Auth.AdminUserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
* 管理者用ログイン認証設定
* */
@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class AdminSecurityConfig {

    private final AdminUserDetailsServiceImpl adminUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final DaoAuthenticationProvider authenticationProvider;
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(adminUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
    @Bean //DI化する。Springに登録必須にて。
    public SecurityFilterChain adminSecurityConfig(HttpSecurity http)throws Exception{
        http
                .authenticationProvider(authenticationProvider)
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

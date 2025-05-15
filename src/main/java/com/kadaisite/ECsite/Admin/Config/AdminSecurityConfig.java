package com.kadaisite.ECsite.Admin.Config;

import com.kadaisite.ECsite.Admin.Auth.AdminUserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/*
* 管理者用ログイン認証設定
* */
@Order(1)
@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class AdminSecurityConfig {

    private final AdminUserDetailsServiceImpl adminUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(adminUserDetailsService);//ユーザーメールとパスワード、権限情報取得
        provider.setPasswordEncoder(passwordEncoder);//ハッシュ化したパスワードで一致するか
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            DaoAuthenticationProvider authenticationProvider)throws Exception{//上記作成したDaoAuthenticationProviderを引数で受け取る。
        return http.getSharedObject(AuthenticationManagerBuilder.class)//共有オブジェクトを取得(Spring Securityの内部で作られてる AuthenticationManagerBuilder を取得)。
                .authenticationProvider(authenticationProvider)//このビルダーに、先ほど受け取った DaoAuthenticationProvider を登録。
                .build();
    }
    @Bean //DI化する。Springに登録必須にて。
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http)throws Exception{
        http
                .securityMatcher("/admin/**")//管理画面はadmin以降のリンクで
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/css/**", "/images/**").permitAll()
                        .requestMatchers( "/admin/login").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/admin/login"))
                )
//        やっぱり自分でログインフォームのバリデーションつけたかったので下記コメントアウト
//                .formLogin(form->form
//                        .loginPage("/admin/login")
//                        .loginProcessingUrl("/admin/login")
//                        .failureUrl("/admin/login?error")
//                        .usernameParameter("email")
//                        .passwordParameter("password")
//                        .defaultSuccessUrl("/admin")
//                        .permitAll()
                .logout((logout)->logout
//                        .logoutUrl("/admin/exit")
                        .logoutSuccessUrl("/admin/logout")
                        .permitAll()
                )
                ;
        return http.build();
    }
}

package com.kadaisite.ECsite.Admin.Controller;

import com.kadaisite.ECsite.Admin.Entity.Admin_users;
import com.kadaisite.ECsite.Admin.Repository.AdminMapper;
import com.kadaisite.ECsite.User.Form.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


//管理者用URL
/*
*
*
* */
@RequiredArgsConstructor
@Controller
public class AdminController {
//    DI化した認証情報をここで使えるように呼び出し。
    private final AuthenticationManager authenticationManager;
    private final AdminMapper adminMapper;

//    ログイン後の管理画面
    @GetMapping("/admin")
    public String adminTop(Model model){
        List<Admin_users> admin = adminMapper.getAllUsers();
        model.addAttribute("adminUsers",admin);
        System.out.println(admin);
        return "/admin/index";
    }
//ログイン画面
    @GetMapping("/admin/login")
    public String AdminLogin(LoginForm loginForm){
        return "/admin/Auth/login";
    }

//    ログイン・バリデーション実装
    @PostMapping("/admin/login")
    public String AdminEntry(@Validated @ModelAttribute LoginForm loginForm,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()) {//未入力などバリデーションエラーがあれば返す。
            return "/admin/Auth/login";
        }
        try {
            Authentication authentication = authenticationManager.authenticate(//作成したトークンで認証を実行。
                    new UsernamePasswordAuthenticationToken(
                            loginForm.getEmail(),
                            loginForm.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);//ログイン認証に成功したユーザーのセッション情報を保持。
        }catch(AuthenticationException e){
            model.addAttribute("error","ログインに失敗しました。再度、メールとパスワードを確認の上、入力してください。");
            return "/admin/Auth/login";
        }
        return "redirect:/admin";
    }

}

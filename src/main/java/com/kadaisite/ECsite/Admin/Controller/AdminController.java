package com.kadaisite.ECsite.Admin.Controller;

import com.kadaisite.ECsite.Admin.Entity.Admin_users;
import com.kadaisite.ECsite.Admin.Repository.AdminMapper;
import com.kadaisite.ECsite.Admin.Service.ECsiteUserService;
import com.kadaisite.ECsite.User.Form.LoginForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


//管理者用URL
/*
*管理画面TOP
* ログイン画面
* 新規ユーザー追加
*
* */
@RequiredArgsConstructor
@Controller
public class AdminController {
//    DI化した認証情報をここで使えるように呼び出し。
    private final AuthenticationManager authenticationManager;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;
    private final ECsiteUserService insertUser;


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
                             HttpServletRequest request,
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
            System.out.println("authentication:"+authentication);
            SecurityContext context = SecurityContextHolder.getContext();
            System.out.println("context:"+context);
            context.setAuthentication(authentication);
//            SecurityContextHolder.setContext(context);
            request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);//ログイン認証に成功したユーザーのセッション情報を保持。

        }catch(AuthenticationException e){
            model.addAttribute("error","ログインに失敗しました。再度、メールとパスワードを確認の上、入力してください。");
            return "/admin/Auth/login";
        }
        return "redirect:/admin";
    }


//設定画面
    @GetMapping("/admin/config")
    public String Config(){
        return "admin/config/config";
    }

//    ログアウト画面
    @GetMapping("/admin/exit")
    public String exit(){
        return "admin/Auth/exit";
    }
//    ログアウト送信
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
    @PostMapping("/admin/exit")
    public String performLogout(Authentication authentication,
                                HttpServletRequest request,
                                HttpServletResponse response,
                                RedirectAttributes redirectAttributes){

//そもそも認証情報が入ってなかったらリダイレクト先（ログアウト画面に飛ばす,ログイン画面でいいかもだけど。）
        if(authentication == null){
            redirectAttributes.addFlashAttribute("error","ログアウトに失敗しました");
            return "redirect:/admin/exit";
        }
        System.out.println("Auth: " + authentication.toString());
        Object principal = authentication.getPrincipal();
        System.out.println("Principal: " + principal);
        this.logoutHandler.logout(request,response,authentication);
        return "redirect:/admin/logout";
    }
//    ログアウト遷移先

    @GetMapping("/admin/logout")
    public String logout(){
        return "admin/Auth/logout";
    }


}

package com.kadaisite.ECsite.User.Repository.Controller;

import com.kadaisite.ECsite.User.Repository.Form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PageMapping {
//    TOP画面
    @GetMapping("/")
    public  String top(){
        return  "/users/index";
    }

//    ログインフォーム
    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm){
        return "/users/Auth/login";
    }
}

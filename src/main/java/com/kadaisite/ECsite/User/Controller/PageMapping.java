package com.kadaisite.ECsite.User.Controller;

import com.kadaisite.ECsite.User.Form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageMapping {
//    TOP画面
    @GetMapping("/")
    public  String top(){
        return  "/users/index";
    }

//    ログインフォーム表示
    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm){
        return "/users/Auth/login";
    }

}

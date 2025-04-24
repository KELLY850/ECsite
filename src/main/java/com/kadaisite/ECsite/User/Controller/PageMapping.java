package com.kadaisite.ECsite.User.Controller;

import com.kadaisite.ECsite.User.Form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
//    結局、バリデーションはVue.jsにした。完全にバリデーションをLoginFormでしたくなる可能性もあるのでこのまま残しとく。
    @PostMapping("/login")
    public  String login(@Validated @ModelAttribute LoginForm loginForm,BindingResult result,Model model){
        if(result.hasErrors()){
            return "/users/Auth/login";
        }
        return "redirect:/";
    }

}

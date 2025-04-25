package com.kadaisite.ECsite.Admin.Controller;

import com.kadaisite.ECsite.User.Form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


//管理者用URL
@Controller
public class AdminController {
    @GetMapping("/admin")
    public String adminTop(){
        return "/admin/index";
    }

    @GetMapping("/admin/login")
    public String AdminLogin(LoginForm loginForm){
        return "/admin/Auth/login";
    }
    @PostMapping("/admin/login")
    public String AdminEntry(@Validated @ModelAttribute LoginForm loginForm,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()) {
            return "/admin/Auth/login";
        }
        return "redirect:/";
    }
}

package com.kadaisite.ECsite.User.Controller.Common;

import com.kadaisite.ECsite.User.Auth.LoginUser;
import com.kadaisite.ECsite.User.Entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@AllArgsConstructor
public class GlovalController {

    @ModelAttribute
    public void GlovalUser(@AuthenticationPrincipal LoginUser loginUser, Model model){
        if(loginUser != null){
            User user=loginUser.getUser();//userエンティティ型にして、そのエンティティにそったログイン情報を入れる。
            model.addAttribute("username",user.getName());//ログインユーザー名をHTMLに返す用
        }else{
            model.addAttribute("username","");
        }
    }
}

package com.kadaisite.ECsite.User.Auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class LoginUser extends User {
    public LoginUser(
            String email,//メールアドレスに変更
            String password,
            //下記は権限
            Collection<? extends GrantedAuthority>authorities){
//        Userに下記を渡す。
        super(email,password,authorities);
    }
    @Override//emailで受け取るためにusernameを返す。
    public  String getUsername(){
        return  super.getUsername();
    }
}

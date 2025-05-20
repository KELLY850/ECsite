package com.kadaisite.ECsite.User.Form;

import com.kadaisite.ECsite.Admin.Validate.UpdatedGroup;
import com.kadaisite.ECsite.User.Validate.AdminNewCreateGroup;
import com.kadaisite.ECsite.User.Validate.CreateGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/*
* ログイン入力フォーム
* String email メールアドレス
* String password パスワード
* */
@Data
public class LoginForm {
    @NotBlank(message = "メールアドレスを入力してください",groups ={CreateGroup.class, UpdatedGroup.class,AdminNewCreateGroup.class})
    @Email(message = "メールアドレスの形式が無効です。",groups ={CreateGroup.class,UpdatedGroup.class,AdminNewCreateGroup.class})
    private String email;
    @NotBlank(message = "パスワードを入力してください。",groups ={CreateGroup.class,AdminNewCreateGroup.class})
    private String password;
}

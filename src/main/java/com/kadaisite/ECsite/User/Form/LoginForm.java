package com.kadaisite.ECsite.User.Form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;


/*
* ログイン入力フォーム
* String email メールアドレス
* String password パスワード
* */
@Data
public class LoginForm {
    @NotBlank(message = "メールアドレスを入力してください")
    @Size(min=1,max=100,message = "{min}〜{max}以内で入力してください。")
    @Email(message = "メールアドレスの形式が無効です。")
    private String email;
    @NotBlank(message = "パスワードを入力してください。")
    private String password;
}

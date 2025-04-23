package com.kadaisite.ECsite.User.Form;

import lombok.Data;

/*
* ログイン入力フォーム
* String email メールアドレス
* String password パスワード
* */
@Data
public class LoginForm {
    private String email;
    private String password;
}

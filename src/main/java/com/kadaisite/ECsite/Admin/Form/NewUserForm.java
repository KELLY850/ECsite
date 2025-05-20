package com.kadaisite.ECsite.Admin.Form;


import com.kadaisite.ECsite.User.Form.LoginForm;
import com.kadaisite.ECsite.User.Validate.AdminNewCreateGroup;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/*
* name:ユーザー名
* user/LoginFormを継承
* */

@Data
@EqualsAndHashCode(callSuper = true)//親クラスのも比較する。
public class NewUserForm extends LoginForm {

    @NotBlank(message = "名前を入力してください",groups = AdminNewCreateGroup.class)
    private String name;

}

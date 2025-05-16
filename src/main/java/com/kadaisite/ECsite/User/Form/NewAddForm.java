package com.kadaisite.ECsite.User.Form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/*
* 新規登録バリデーション
* name　名前
* tel　電話番号
* */

@Data
@EqualsAndHashCode(callSuper = true)
public class NewAddForm extends LoginForm{
    @NotBlank(message = " 名前の前に空白があるか未記入です")
    private String name;
    @NotBlank(message = " 電話番号を入力してください")
    private String tel;

}

package com.kadaisite.ECsite.User.Form;

import com.kadaisite.ECsite.Admin.Validate.UpdatedGroup;
import com.kadaisite.ECsite.User.Validate.CreateGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(groups = UpdatedGroup.class)
    private Long id;
    @NotBlank(message = " 名前の前に空白があるか未記入です",groups ={CreateGroup.class,UpdatedGroup.class})
    private String name;
    @NotBlank(message = " 電話番号を入力してください",groups ={CreateGroup.class,UpdatedGroup.class})
    private String tel;

}

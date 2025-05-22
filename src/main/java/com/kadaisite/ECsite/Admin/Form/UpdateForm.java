package com.kadaisite.ECsite.Admin.Form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateForm {
    private Long id;
    @NotBlank(message = " 名前の前に空白があるか未記入です")
    private String name;
    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "メールアドレスの形式が無効です。")
    private String email;
    private String role;
}

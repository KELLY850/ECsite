package com.kadaisite.ECsite.Admin.Form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
/*
* 新カテゴリの追加フォーム
* name:カテゴリ名
* */
@Data
public class CategoryForm {
    private Long id;
    @NotBlank(message = "空白が入っていたり、未入力となっています")
    private String name;
}

package com.kadaisite.ECsite.Admin.Form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/*
* 商品登録用フォーム（バリデーション込み）
* */
@Data
public class ProductForm {
  @NotBlank(message = "空白が入っていたり、未入力となっています")
  private String name;
  @Min(value = 0, message = "0以上の数値を入力してください")
  @NotNull(message = "数値を入力してください")
  private  Integer price;
  @Min(value = 0, message = "0以上の数値を入力してください")
  @NotNull(message = "数値を入力してください")
  private Integer stock;
  @Size(max = 1000,message = "{max}を超えての入力はできません")
  private String description;
//  登録用カテゴリー
  private List<Long> categoryIds =new ArrayList<>();
//  画像登録用複数
  private List<MultipartFile> images;
}

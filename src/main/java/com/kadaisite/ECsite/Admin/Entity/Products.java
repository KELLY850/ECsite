package com.kadaisite.ECsite.Admin.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

//商品テーブル用エンティティ
/*
* name 商品名
* price　金額
* stock　在庫
* description 商品説明
* createdAt　登録日
* updatedAt 更新日
* deletedAt 削除日
* */
@Data
public class Products {
    private Long id;
    private String name;
    private  Integer price;
    private  Integer stock;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deletedAt;
    private Integer deleteFlg;
//    1対多用に下記作成。中間テーブルのJOIN結果をを格納するために必要。
    private List<Categories> categoriesList;
//    1対多用に下記作成。商品画像
    private List<Product_images> images;
}

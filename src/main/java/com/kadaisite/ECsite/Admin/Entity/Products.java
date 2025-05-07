package com.kadaisite.ECsite.Admin.Entity;

import lombok.Data;

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
    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;
    private java.sql.Timestamp deletedAt;
    private Integer deleteFlg;

}

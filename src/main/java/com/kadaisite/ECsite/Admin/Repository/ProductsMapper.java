package com.kadaisite.ECsite.Admin.Repository;

import com.kadaisite.ECsite.Admin.Entity.Admin_users;
import com.kadaisite.ECsite.Admin.Entity.Products;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
* 商品の登録・リスト
* */
@Mapper
public interface ProductsMapper {
//    商品のリストを返す。
    List<Products> getAllProducts();
    //  商品の新規登録 登録された数を返す為にint型
    int insertProduct(Products products);
}

package com.kadaisite.ECsite.Admin.Service;

import com.kadaisite.ECsite.Admin.Entity.Products;
import com.kadaisite.ECsite.Admin.Repository.ProductCategoryMapper;
import com.kadaisite.ECsite.Admin.Repository.ProductsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*
* 商品登録、商品一覧、
* */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductsMapper productsMapper;
    private final ProductCategoryMapper productCategoryMapper;
    private final ProductImagesService productImagesService;
    private final CategoryService categoryService;
    /*
    * products　登録する商品情報(productForm)
    * categoryIds　商品に紐づいたカテゴリーのID List型
    * images　商品に紐づいた商品画像の有無　List型
    * */
    public void save(Products products, List<Long>categoryIds, List<MultipartFile> images){
        int product = productsMapper.insertProduct(products);
        System.out.println("登録結果：" + product);
        if(product != 1){
            throw new RuntimeException("登録に失敗しました");
        }
//        商品IDを取得。
        Long productId = products.getId();
        System.out.println("数字を出せ商品IDの！！"+productId);
        System.out.println("よこせ、categoryIdを！！"+categoryIds);
//        カテゴリーを商品とカテゴリテーブル（中間テーブル）に保存。
        categoryService.saveProductCategory(categoryIds,productId);
//        画像登録
        productImagesService.saveImage(productId,images);
    }
//    商品一覧を表示
    public List<Products> productList(){
        return productsMapper.getAllProducts();
    }
}

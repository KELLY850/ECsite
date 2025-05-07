package com.kadaisite.ECsite.Admin.Service;

import com.kadaisite.ECsite.Admin.Entity.Products;
import com.kadaisite.ECsite.Admin.Repository.ProductCategoryMapper;
import com.kadaisite.ECsite.Admin.Repository.ProductsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductsMapper productsMapper;
    private final ProductCategoryMapper productCategoryMapper;
    public void save(Products products,List<Long>categoryIds){
        int product = productsMapper.insertProduct(products);
        System.out.println("登録結果：" + product);
        if(product != 1){
            throw new RuntimeException("登録に失敗しました");
        }
//        商品IDを取得。
        Long productId = products.getId();
        System.out.println("数字を出せ商品IDの！！"+productId);
        System.out.println("よこせ、categoryIdを！！"+categoryIds);
        if (categoryIds != null){
            for(Long categoryId :categoryIds){
                productCategoryMapper.insertCategoryProduct(productId,categoryId);
            }
        }
    }

//    商品一覧を表示
    public List<Products> productList(){
        return productsMapper.getAllProducts();
    }
}

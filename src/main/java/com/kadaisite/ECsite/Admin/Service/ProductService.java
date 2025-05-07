package com.kadaisite.ECsite.Admin.Service;

import com.kadaisite.ECsite.Admin.Entity.Products;
import com.kadaisite.ECsite.Admin.Repository.ProductsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductsMapper productsMapper;
    public void save(Products products){
        int product = productsMapper.insertProduct(products);
        System.out.println("登録結果：" + product);
        if(product != 1){
            throw new RuntimeException("登録に失敗しました");
        }
    }
    public List<Products> productList(){
        return productsMapper.getAllProducts();
    }
}

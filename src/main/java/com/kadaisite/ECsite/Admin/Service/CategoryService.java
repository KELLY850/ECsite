package com.kadaisite.ECsite.Admin.Service;

import com.kadaisite.ECsite.Admin.Entity.Categories;
import com.kadaisite.ECsite.Admin.Repository.CategoriesMapper;
import com.kadaisite.ECsite.Admin.Repository.ProductCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoriesMapper categoriesMapper;
    private final ProductCategoryMapper productCategoryMapper;
//    カテゴリ登録
    public void save(Categories categories){
        int category = categoriesMapper.insertCategories(categories);
        if(category != 1){
            throw new RuntimeException("登録に失敗しました");
        }
    }
//カテゴリの一覧
    public List<Categories> CategoriesList(){
        return categoriesMapper.getAllCategories();
    }
//    商品にカテゴリを紐付け。
    public void saveProductCategory(List<Long>categoryIds,Long productId){
        if (categoryIds != null){
            for(Long categoryId :categoryIds){
                productCategoryMapper.insertCategoryProduct(productId,categoryId);
            }
        }
    }

}

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
    public int save(Categories categories){
        int category = categoriesMapper.insertCategories(categories);
        if(category > 0){
            return 1;
        }
        return 0;
    }
//カテゴリの一覧
    public List<Categories> CategoriesList(){
        return categoriesMapper.getAllCategories();
    }
//    カテゴリID取得
    public Categories categoryId(Long id){
        return categoriesMapper.selectById(id);
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

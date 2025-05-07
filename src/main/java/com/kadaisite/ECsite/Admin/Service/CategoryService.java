package com.kadaisite.ECsite.Admin.Service;

import com.kadaisite.ECsite.Admin.Entity.Categories;
import com.kadaisite.ECsite.Admin.Repository.CategoriesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoriesMapper categoriesMapper;
//    カテゴリ登録
    public void save(Categories categories){
        int category = categoriesMapper.insertCategories(categories);
        if(category != 1){
            throw new RuntimeException("登録に失敗しました");
        }
    }

    public List<Categories> CategoriesList(){
        return categoriesMapper.getAllCategories();
    }


}

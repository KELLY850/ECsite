package com.kadaisite.ECsite.Admin.Common;

import com.kadaisite.ECsite.Admin.Entity.Categories;
import com.kadaisite.ECsite.Admin.Repository.CategoriesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*
* カテゴリーのコントローラーでよく使うので作成。
* */
@Component
@RequiredArgsConstructor
public class CategoriesList {
    private final CategoriesMapper categoriesMapper;

    public List<Categories> categoryList(){
        List<Categories>categories=categoriesMapper.getAllCategories();
        if(categories==null){
            categories=new ArrayList<>();
        }
        return categories;
    }
}

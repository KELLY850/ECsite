package com.kadaisite.ECsite.Admin.Repository;

import com.kadaisite.ECsite.Admin.Entity.Categories;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoriesMapper {
    //新規カテゴリー登録の数確認（1件ずつ）
    int insertCategories(Categories categories);
//    全部のカテゴリーを取得
    List<Categories> getAllCategories();
}

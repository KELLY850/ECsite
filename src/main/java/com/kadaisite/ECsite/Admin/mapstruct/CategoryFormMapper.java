package com.kadaisite.ECsite.Admin.mapstruct;

import com.kadaisite.ECsite.Admin.Entity.Categories;
import com.kadaisite.ECsite.Admin.Form.CategoryForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/*
* Categoriesテーブル用マッパーストラクト
* */
@Mapper(componentModel = "spring")
//interface にしておくことで、MapStruct が中身を自動実装（CategoryFormMapperImpl を生成）できる
public interface CategoryFormMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "deleteFlg", ignore = true)
    Categories toEntity(CategoryForm categoryForm);
    CategoryForm toForm(Categories categories);
}


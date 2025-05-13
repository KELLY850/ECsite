package com.kadaisite.ECsite.Admin.Repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductCategoryMapper {
//    中間テーブルに下記IDを入れてねという書き方。この二つを引数にするのでxmlもparameterType="map"となる。
    int insertCategoryProduct(@Param("productId")Long productId,@Param("categoryId")Long categoryId);
}

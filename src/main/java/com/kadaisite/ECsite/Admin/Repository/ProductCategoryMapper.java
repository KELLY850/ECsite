package com.kadaisite.ECsite.Admin.Repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductCategoryMapper {
    int insertCategoryProduct(@Param("productId")Long productId,@Param("categoryId")Long categoryId);
}

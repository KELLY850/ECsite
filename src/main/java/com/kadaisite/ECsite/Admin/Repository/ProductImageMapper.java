package com.kadaisite.ECsite.Admin.Repository;

import com.kadaisite.ECsite.Admin.Entity.Product_images;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductImageMapper {
    int insertImages (Product_images image);
}

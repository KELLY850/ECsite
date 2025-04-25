package com.kadaisite.ECsite.Admin.Repository;

import com.kadaisite.ECsite.Admin.Entity.Admin_users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    Admin_users selectByUserEmail(String email);
}

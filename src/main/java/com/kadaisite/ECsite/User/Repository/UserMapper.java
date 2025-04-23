package com.kadaisite.ECsite.User.Repository;

import com.kadaisite.ECsite.User.Entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
//    ユーザーのemailで検索
    User selectByUserEmail(String email);
}

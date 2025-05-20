package com.kadaisite.ECsite.Admin.Repository;

import com.kadaisite.ECsite.User.Entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminUsersMapper {
//全お客様情報取得
  List<User> getAllUsers();
//  選択したお客様情報取得
  User selectById(Long id);

  int updateUser(User user);
}

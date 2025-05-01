package com.kadaisite.ECsite.Admin.Repository;

import com.kadaisite.ECsite.Admin.Entity.Admin_users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
//    全ユーザー取得
    List<Admin_users> getAllUsers();
//    メールアドレスに合致するユーザーを取得
    Admin_users selectByUserEmail(String email);
//  管理者ユーザーの新規登録 登録された数を返す為にint型
    int insertUser(Admin_users admin_users);
}

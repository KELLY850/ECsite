package com.kadaisite.ECsite.Admin.Service;

import com.kadaisite.ECsite.Admin.Entity.Admin_users;
import com.kadaisite.ECsite.Admin.Form.NewUserForm;
import com.kadaisite.ECsite.Admin.Repository.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ECsiteUserService {
    private final PasswordEncoder passwordEncoder;
    private final AdminMapper adminMapper;

    public void register(NewUserForm newUserForm){
        //            新規登録するエンティティのインスタンス作成
        Admin_users admin = new Admin_users();
        //入力フォームの内容を一つずつ入れていく。
        admin.setName(newUserForm.getName());
        admin.setEmail(newUserForm.getEmail());
        String pas = passwordEncoder.encode(newUserForm.getPassword());//ハッシュ化する
        admin.setPassword(pas);
        //上記のデータをすべて詰めてDBに保存するし、その保存数についてのチェックをする。
        int newAdd = adminMapper.insertUser(admin);
        if(newAdd != 1){
            throw new RuntimeException("ユーザー登録に失敗しました");
        }
//            System.out.println(admin.getId());IDの連番取得OK
    }

}

package com.kadaisite.ECsite.Admin.Service;

import com.kadaisite.ECsite.Admin.Common.DiffParts;
import com.kadaisite.ECsite.Admin.Entity.Admin_users;
import com.kadaisite.ECsite.Admin.Form.NewUserForm;
import com.kadaisite.ECsite.Admin.Repository.AdminMapper;
import com.kadaisite.ECsite.Admin.Repository.AdminUsersMapper;
import com.kadaisite.ECsite.User.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ECsiteUserService {
    private final PasswordEncoder passwordEncoder;
    private final AdminMapper adminMapper;
    private final AdminUsersMapper adminUsersMapper;

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

//    ユーザー情報の取得
    public User selectId(Long id){
        User user = adminUsersMapper.selectById(id);
        if (user == null){
            throw new RuntimeException("ユーザー情報の取得に失敗しました");
        }
        return user;
    }

//    ユーザー情報の更新
    public int updatedUser(User user){
//        boolean result=false;
        User oldUser =adminUsersMapper.selectById(user.getId());
        boolean diff = DiffParts.diff(oldUser,user,(diffDB, newUser) ->{
                    diffDB.append("name",oldUser.getName(),newUser.getName());
                    diffDB.append("email",oldUser.getEmail(),newUser.getEmail());
                    diffDB.append("tel",oldUser.getTel(),newUser.getTel());
                });
        if(!diff){
            return 0;
        }
        int result =adminUsersMapper.updateUser(user);
        return 1;



//        入力値が以前と違ったらtrueを返す。
//        if(!oldUser.getName().equals(user.getName())){
//            oldUser.setName(user.getName());
//            result= true;
//        }
//        if (!oldUser.getEmail().equals(user.getEmail())){
//            oldUser.setEmail(user.getEmail());
//            result=true;
//        }
//        if (!oldUser.getTel().equals(user.getTel())){
//            oldUser.setTel(user.getTel());
//            result=true;
//        }
//        if(result){
//           return adminUsersMapper.updateUser(oldUser);
//        }
//        return 0;

    }

}

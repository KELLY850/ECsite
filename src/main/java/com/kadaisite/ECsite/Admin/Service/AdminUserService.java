package com.kadaisite.ECsite.Admin.Service;

import com.kadaisite.ECsite.Admin.Common.DiffParts;
import com.kadaisite.ECsite.Admin.Entity.Admin_users;
import com.kadaisite.ECsite.Admin.Repository.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserService {
    private final AdminMapper adminMapper;


    public int updateAdmin(Admin_users admin_users){
//        ここで元々のデータを取得。
        Admin_users adminDB =adminMapper.selectbyId(admin_users.getId());
        boolean diff = DiffParts.diff(adminDB,
                admin_users,
                (diffDB, adminNewUser) ->
                {
                    diffDB.append("name",adminDB.getName(),adminNewUser.getName());
                    diffDB.append("email",adminDB.getEmail(),adminNewUser.getEmail());
                    diffDB.append("role",adminDB.getRole(),adminNewUser.getRole());
                });
        if(!diff){
            return 0;
        }
        //        差分があればtrueということで更新をかける。
        int result=adminMapper.updateAdminUser(admin_users);
        if (result != 1) {
            throw new RuntimeException("更新に失敗しました");
        }
        return 1;
    }
}

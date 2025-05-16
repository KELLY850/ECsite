package com.kadaisite.ECsite.User.Service;

import com.kadaisite.ECsite.Config.PasswordConfig;
import com.kadaisite.ECsite.User.Entity.User;
import com.kadaisite.ECsite.User.Repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordConfig passwordConfig;

    public void save(User user){
        String pass= passwordConfig.passwordEncoder().encode(user.getPassword());
        user.setPassword(pass);
        int result = userMapper.insertUser(user);
        if(result !=1){
            throw new RuntimeException("登録に失敗しました");
        }
    }
}

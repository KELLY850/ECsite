package com.kadaisite.ECsite.Admin.Auth;

import com.kadaisite.ECsite.Admin.Repository.AdminMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AdminUserDetailsServiceImpl implements UserDetailsService {
    public final AdminMapper adminMapper;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return null;
    }
}

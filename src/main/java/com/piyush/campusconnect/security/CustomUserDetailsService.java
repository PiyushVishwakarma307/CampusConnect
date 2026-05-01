package com.piyush.campusconnect.security;

import com.piyush.campusconnect.entity.User;
import com.piyush.campusconnect.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String rollNo) throws UsernameNotFoundException {

        User user = userRepository.findByRollNo(Integer.parseInt(rollNo)).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(String.valueOf(user.getRollNo()))
                .password(user.getPassword())
                .roles(user.getRole()) // IMPORTANT
                .build();
    }
}
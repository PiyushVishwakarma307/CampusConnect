package com.piyush.campusconnect.service.impl;
import com.piyush.campusconnect.entity.User;
import com.piyush.campusconnect.repository.UserRepo;
import com.piyush.campusconnect.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder encoder;

    public User authenticate(int rollNo, String password){
        User user = userRepo.findByRollNo(rollNo).orElseThrow();
        if(encoder.matches(password, user.getPassword())){
            return user;
        }else {
            return null;
        }
    }
}

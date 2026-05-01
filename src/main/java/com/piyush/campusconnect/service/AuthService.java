package com.piyush.campusconnect.service;

import com.piyush.campusconnect.entity.User;

public interface AuthService {
    public User authenticate(int rollNo, String password);
}

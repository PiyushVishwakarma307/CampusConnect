package com.piyush.campusconnect;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String b = encoder.encode("Piyush@123");
        System.out.println(b);
        System.out.println((new String(b.getBytes())));
    }
}

package com.piyush.campusconnect.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "roll_no")
    private int rollNo;
    private String email;
    private String password;
    private String role;
    private Boolean enabled;
    @Column(name = "is_first_login")
    private Boolean isFirstLogin;
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDate createdAt;
}

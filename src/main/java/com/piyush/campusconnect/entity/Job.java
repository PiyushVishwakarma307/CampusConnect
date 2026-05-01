package com.piyush.campusconnect.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Getter
@Setter
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String company;
    private String description;
    private String location;
    private String type;
    private LocalDateTime deadline;
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}

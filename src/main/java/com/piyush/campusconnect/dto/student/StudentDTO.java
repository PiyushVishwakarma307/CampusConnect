package com.piyush.campusconnect.dto.student;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentDTO {
    private String firstName;
    private String lastName;
    private int rollNumber;
    private String email;
    private String branch;
    private Integer semester;
    private Long phone;
    private LocalDate createdAt;
    private int totalAssignments;
    private int completedAssignments;
    private int appliedJobs;
    private int overdueCount;
}

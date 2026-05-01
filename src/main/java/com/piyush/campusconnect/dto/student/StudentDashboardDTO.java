package com.piyush.campusconnect.dto.student;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StudentDashboardDTO {

    // Student Info
    private String firstName;
    private String lastName;

    // Stats
    private int totalAssignments;
    private int dueSoon;
    private int openJobs;
    private int overdue;

    // Lists
    private List<AssignmentDTO> recentAssignments = new ArrayList<>();
    private List<JobDTO> latestJobs = new ArrayList<>();
}
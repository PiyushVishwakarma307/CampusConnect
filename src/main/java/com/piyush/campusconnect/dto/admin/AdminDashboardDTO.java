package com.piyush.campusconnect.dto.admin;

import com.piyush.campusconnect.dto.student.AssignmentDTO;
import com.piyush.campusconnect.dto.student.StudentDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminDashboardDTO {
    private String firstName;
    private long totalStudents;
    private long totalAssignments;
    private long totalJobs;
    private long overdueCount;
    private List<AssignmentDTO> recentAssignments;
    private List<StudentDTO> recentStudents;
}

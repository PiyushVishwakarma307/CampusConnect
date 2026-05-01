package com.piyush.campusconnect.service.student;

import com.piyush.campusconnect.dto.student.AssignmentDTO;
import com.piyush.campusconnect.dto.student.JobDTO;
import com.piyush.campusconnect.dto.student.StudentDTO;
import com.piyush.campusconnect.dto.student.StudentDashboardDTO;
import com.piyush.campusconnect.entity.Assignment;
import com.piyush.campusconnect.entity.Student;
import com.piyush.campusconnect.entity.User;
import com.piyush.campusconnect.enums.AssignmentStatus;
import com.piyush.campusconnect.repository.AssignmentRepo;
import com.piyush.campusconnect.repository.JobRepo;
import com.piyush.campusconnect.repository.StudentRepo;
import com.piyush.campusconnect.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;
    @Autowired
    private AssignmentRepo assignmentRepository;
    @Autowired
    private JobRepo jobRepository;
    @Autowired
    private StudentRepo studentRepository;
    @Autowired
    AssignmentService assignmentService;
    @Autowired
    JobService jobService;

    public StudentDashboardDTO getDashboardData(User user) {

        StudentDashboardDTO dto = new StudentDashboardDTO();

        String[] parts = user.getName() != null
                ? user.getName().trim().split(" ", 2)
                : new String[]{"", ""};

        dto.setFirstName(parts[0]);
        dto.setLastName(parts.length > 1 ? parts[1] : "");

        List<Assignment> assignments = assignmentRepository.findTop5ByOrderByCreatedAtDesc();

        dto.setTotalAssignments((int) assignmentRepository.count());

        dto.setDueSoon((int)assignmentRepository.countByDueDateBetween(LocalDateTime.now(), LocalDateTime.now().plusDays(3)));

        dto.setOverdue((int) assignmentRepository.countByDueDateBefore(LocalDateTime.now()));

        dto.setOpenJobs((int) jobRepository.count());

        // -------------------------
        // Recent Assignments
        // -------------------------
        List<AssignmentDTO> assignmentDTOs = assignments.stream()
                .map(assignment -> assignmentService.mapToDTO(assignment))
                .toList();

        dto.setRecentAssignments(assignmentDTOs);

        // -------------------------
        // Jobs
        // -------------------------
        List<JobDTO> jobDTOs = jobRepository.findTop5ByOrderByCreatedAtDesc()
                .stream()
                .map(job-> jobService.mapToDTO(job))
                .toList();

        dto.setLatestJobs(jobDTOs);

        return dto;
    }

    // =========================
    // USER FETCH
    // =========================
    public User getUser(int rollNo) {
        return userRepository.findByRollNo(rollNo).orElseThrow(()-> new RuntimeException("No user found by the roll no : " + rollNo));
    }

    // =========================
    // MAPPERS
    // =========================


    // =========================
    // STATUS LOGIC (FIXED + ENUM)
    // =========================



}
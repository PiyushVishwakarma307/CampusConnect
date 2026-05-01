package com.piyush.campusconnect.controller;

import com.piyush.campusconnect.dto.student.AssignmentDTO;
import com.piyush.campusconnect.dto.student.StudentDTO;
import com.piyush.campusconnect.dto.student.StudentDashboardDTO;
import com.piyush.campusconnect.enums.AssignmentStatus;
import com.piyush.campusconnect.service.student.AssignmentService;
import com.piyush.campusconnect.service.AuthService;
import com.piyush.campusconnect.service.student.JobService;
import com.piyush.campusconnect.service.student.StudentService;
import com.piyush.campusconnect.service.student.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AssignmentService assignmentService;
    @Autowired
    private JobService jobService;

    @GetMapping({"", "/" , "/dashboard"}) public String studentDashboard(Model model, Authentication auth) {

        StudentDashboardDTO dashboard =
                userService.getDashboardData(userService.getUser(Integer.parseInt(auth.getName())));

        model.addAttribute("dashboard", dashboard);
        return "student/dashboard";
    }

    @GetMapping("/assignments")
    public String getAssignments(
            @RequestParam(required = false) AssignmentStatus status,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Page<AssignmentDTO> assignments;
        if (status == null) {
            assignments = assignmentService.getAllAssignments(page, 10 );
        } else {
            assignments = assignmentService.getAssignmentsByStatus(status, page, 10);
        }

        model.addAttribute("assignments", assignments.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", assignments.getTotalPages());
        return "student/assignments";
    }

    @GetMapping("/jobs")
    public String jobPage(Model model){
        model.addAttribute("jobs", jobService.getAllJobs());
        return "student/jobs";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, Authentication auth){
        StudentDTO dto = studentService.getStudentData(Integer.parseInt(auth.getName()));
        model.addAttribute("student", dto);
        model.addAttribute("studentForm", dto);
        return "student/profile";
    }

    @GetMapping("/jobs/{id}")
    public String applyForJob(@PathVariable Long id) {
        return "soon";
    }

    @GetMapping("/assignments/{id}")
    public String viewAssignmentDetails(@PathVariable Long id) {
        return "soon";
    }
}

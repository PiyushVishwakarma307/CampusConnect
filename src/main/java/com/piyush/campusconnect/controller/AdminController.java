package com.piyush.campusconnect.controller;

import com.piyush.campusconnect.dto.admin.AdminDashboardDTO;
import com.piyush.campusconnect.entity.Assignment;
import com.piyush.campusconnect.entity.Job;
import com.piyush.campusconnect.service.admin.AdminService;
import com.piyush.campusconnect.service.student.AssignmentService;
import com.piyush.campusconnect.service.student.JobService;
import com.piyush.campusconnect.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    AdminService adminService;

    @Autowired
    JobService jobService;

    @Autowired
    StudentService studentService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {

        AdminDashboardDTO dashboard = adminService.getDashboardData(Integer.parseInt(principal.getName()));

        model.addAttribute("dashboard", dashboard);
        return "admin/dashboard";
    }

    @GetMapping("/assignments")
    public String assignments(Model model) {
        model.addAttribute("assignments", assignmentService.getAllAssignments());
        return "admin/assignments";
    }

    @PostMapping("/assignments/{id}/delete")
    public String deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignmentById(id);
        return "redirect:/admin/assignments";
    }

    @GetMapping("/assignments/new")
    public String newAssignmentForm(Model model) {
        model.addAttribute("assignment", new Assignment());
        return "admin/assignment-form";
    }

    @PostMapping("/assignments/save")
    public String saveAssignment(Assignment assignment){
        assignmentService.saveAssignment(assignment);
        return "redirect:/admin/assignments";
    }


    @GetMapping("/students")
    public String students(Model model){
        model.addAttribute("students",studentService.getAllStudentsData());
        List<String> branches = new ArrayList<>();
        branches.addAll(Arrays.asList("CSE", "IT", "ECE", "ME", "CE"));
        model.addAttribute("branches",branches);
        return "admin/students";
    }

    @GetMapping("/jobs")
    public String jobs(Model model){
        model.addAttribute("jobs", jobService.getAllJobs());
        return "admin/jobs";
    }

    @PostMapping("/jobs/{id}/delete")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJobById(id);
        return "redirect:/admin/jobs";
    }

    @PostMapping("/jobs/save")
    public String saveJob(Job job){
        jobService.saveJob(job);
        return "redirect:/admin/jobs";
    }

    @GetMapping("/jobs/new")
    public String newJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "admin/job-form";
    }


}

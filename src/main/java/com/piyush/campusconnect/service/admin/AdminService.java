package com.piyush.campusconnect.service.admin;

import com.piyush.campusconnect.dto.admin.AdminDashboardDTO;
import com.piyush.campusconnect.dto.student.AssignmentDTO;
import com.piyush.campusconnect.dto.student.JobDTO;
import com.piyush.campusconnect.dto.student.StudentDTO;
import com.piyush.campusconnect.entity.User;
import com.piyush.campusconnect.repository.AssignmentRepo;
import com.piyush.campusconnect.repository.JobRepo;
import com.piyush.campusconnect.repository.StudentRepo;
import com.piyush.campusconnect.repository.UserRepo;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepo userRepository;
    @Autowired
    private StudentRepo studentRepository;
    @Autowired
    private AssignmentRepo assignmentRepository;
    @Autowired
    private JobRepo jobRepository;


    public AdminDashboardDTO getDashboardData(int rollNo) {

        AdminDashboardDTO dto = new AdminDashboardDTO();

        // 👤 Admin info
        dto.setFirstName(userRepository.findByRollNo(rollNo).orElseThrow(()-> new RuntimeException("No User Found")).getName());

        // 📊 Counts
        dto.setTotalStudents(studentRepository.count());
        dto.setTotalAssignments(assignmentRepository.count());
        dto.setTotalJobs(jobRepository.count());

        // ⏰ Overdue assignments
        dto.setOverdueCount(assignmentRepository.countOverdue());

        // 📚 Recent Assignments
        List<AssignmentDTO> recentAssignments =
                assignmentRepository.findTop5ByOrderByCreatedAtDesc()
                        .stream()
                        .map(a -> {
                            AssignmentDTO adto = new AssignmentDTO();
                            adto.setId(a.getId());
                            adto.setTitle(a.getTitle());
                            adto.setDueDate(a.getDueDate());
                            return adto;
                        })
                        .toList();

        dto.setRecentAssignments(recentAssignments);

        // 👥 Recent Students
        List<StudentDTO> recentStudents =
                studentRepository.findStudentsWithUser(PageRequest.of(0,5))
                        .stream()
                        .map(s -> {
                            StudentDTO sdto = new StudentDTO();
                            User u = s.getUser();
                            String[] parts = u.getName() != null
                                    ? u.getName().trim().split(" ", 2)
                                    : new String[]{"", ""};

                            sdto.setFirstName(parts[0]);
                            sdto.setLastName(parts.length > 1 ? parts[1] : "");
                            sdto.setEmail(u.getEmail());
                            sdto.setBranch(s.getBranch());
                            return sdto;
                        })
                        .toList();

        dto.setRecentStudents(recentStudents);

        return dto;
    }



}
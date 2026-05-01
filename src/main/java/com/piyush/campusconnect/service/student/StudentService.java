package com.piyush.campusconnect.service.student;

import com.piyush.campusconnect.dto.student.StudentDTO;
import com.piyush.campusconnect.entity.Student;
import com.piyush.campusconnect.entity.User;
import com.piyush.campusconnect.repository.StudentRepo;
import com.piyush.campusconnect.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepository;
//    @Autowired
//    UserRepo userRepository;

    public List<StudentDTO> getAllStudentsData(){
        return  studentRepository.findStudentsWithUser().stream().map(this::mapToDTO).toList();
    }

    public StudentDTO mapToDTO(Student student){
        StudentDTO dto = new StudentDTO();
        User user = student.getUser();

        String[] nameParts = user.getName().split(" ", 2);
        dto.setFirstName(nameParts[0]);
        dto.setLastName(nameParts.length > 1 ? nameParts[1] : "");

        dto.setRollNumber(user.getRollNo());
        dto.setEmail(user.getEmail());
        dto.setPhone(student.getPhone());
        dto.setBranch(student.getBranch());
        dto.setSemester(student.getSemester());

        dto.setCreatedAt(user.getCreatedAt());

        dto.setTotalAssignments(10); // Real logic to be added later

        dto.setCompletedAssignments(5);  // Real logic to be added later

        dto.setAppliedJobs(10);  // Real logic to be added later

        dto.setOverdueCount(2);  // Real logic to be added later

        return dto;

    }

    public StudentDTO getStudentData(int rollNo) {
        return mapToDTO(studentRepository.findStudentWithUserByRollNo((long)rollNo));
    }
}

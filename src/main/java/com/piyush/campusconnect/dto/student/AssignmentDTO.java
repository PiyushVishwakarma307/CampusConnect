package com.piyush.campusconnect.dto.student;

import com.piyush.campusconnect.enums.AssignmentStatus;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AssignmentDTO {
    private Long id;
    private String title;
    private String description;
    private String subject;
    private LocalDateTime dueDate;
    private AssignmentStatus status; // ACTIVE, DUE_SOON, OVERDUE
}
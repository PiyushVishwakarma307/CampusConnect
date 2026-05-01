package com.piyush.campusconnect.service.student;

import com.piyush.campusconnect.dto.student.AssignmentDTO;
import com.piyush.campusconnect.entity.Assignment;
import com.piyush.campusconnect.enums.AssignmentStatus;
import com.piyush.campusconnect.repository.AssignmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepo assignmentRepository;

    public List<AssignmentDTO> getAllAssignments() {
        return assignmentRepository.findAllByOrderByCreatedAtDesc().stream().map(this::mapToDTO).toList();
    }

    // =========================
    // 1. GET ALL (PAGINATED)
    // =========================
    public Page<AssignmentDTO> getAllAssignments(int page, int size) {

        Page<Assignment> assignments =
                assignmentRepository.findAll(PageRequest.of(page, size));

        return assignments.map(this::mapToDTO);
    }

    // =========================
    // 2. FILTER BY STATUS (FIXED)
    // =========================
    public Page<AssignmentDTO> getAssignmentsByStatus(AssignmentStatus status, int page, int size) {

        // Step 1: get paginated data first (NOT all data)
        Page<Assignment> assignments =
                assignmentRepository.findAll(PageRequest.of(page, size));

        // Step 2: map + filter in-memory (only current page)
        List<AssignmentDTO> filtered = assignments.stream()
                .map(this::mapToDTO)
                .filter(a -> a.getStatus() == status)
                .toList();

        return new PageImpl<>(
                filtered,
                PageRequest.of(page, size),
                assignments.getTotalElements()
        );
    }

    // =========================
    // 3. MAPPER
    // =========================
    public AssignmentDTO mapToDTO(Assignment a) {

        AssignmentDTO dto = new AssignmentDTO();

        dto.setId(a.getId());
        dto.setTitle(a.getTitle());
        dto.setDescription(a.getDescription());
        dto.setDueDate(a.getDueDate());

        dto.setStatus(calculateStatus(a.getDueDate()));

        return dto;
    }

    // =========================
    // 4. STATUS LOGIC (GOOD)
    // =========================
    public AssignmentStatus calculateStatus(LocalDateTime dueDate) {

        LocalDateTime now = LocalDateTime.now();

        if (dueDate.isBefore(now)) {
            return AssignmentStatus.OVERDUE;
        } else if (!dueDate.isAfter(now.plusDays(3))) {
            return AssignmentStatus.DUE_SOON;
        } else {
            return AssignmentStatus.ACTIVE;
        }
    }



    public void deleteAssignmentById(Long id) {
        assignmentRepository.deleteById(id);
    }

    public void saveAssignment(Assignment assignment) {
        assignmentRepository.save(assignment);
    }
}
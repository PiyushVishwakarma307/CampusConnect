package com.piyush.campusconnect.repository;

import com.piyush.campusconnect.entity.Assignment;
import com.piyush.campusconnect.entity.User;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AssignmentRepo extends JpaRepository<Assignment, Long> {
    Page<Assignment> findAll(@NonNull Pageable pageable);
    List<Assignment> findTop5ByOrderByCreatedAtDesc();

    @Query("SELECT COUNT(a) FROM Assignment a WHERE a.dueDate < CURRENT_DATE")
    long countOverdue();
    List<Assignment> findAllByOrderByCreatedAtDesc();

    long countByDueDateBetween(LocalDateTime start, LocalDateTime end);

    long countByDueDateBefore(LocalDateTime dateTime);

}

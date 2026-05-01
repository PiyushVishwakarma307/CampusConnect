package com.piyush.campusconnect.repository;

import com.piyush.campusconnect.entity.Assignment;
import com.piyush.campusconnect.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepo extends JpaRepository<Job, Long> {
    List<Job> findTop5ByOrderByCreatedAtDesc();
}

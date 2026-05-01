package com.piyush.campusconnect.repository;

import com.piyush.campusconnect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByRollNo(Integer rollNo);
    List<User> findByRole(String role);
    Optional<User> findByEmail(String email);
    List<User> findTop5ByOrderByCreatedAtDesc();
}

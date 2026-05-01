package com.piyush.campusconnect.repository;

import com.piyush.campusconnect.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {
    @Query("""
    SELECT s FROM Student s
    JOIN FETCH s.user u
    ORDER BY u.createdAt DESC
""")
    List<Student> findStudentsWithUser(Pageable pageable);
    @Query("""
    SELECT s FROM Student s
    JOIN FETCH s.user u
    ORDER BY u.createdAt DESC
""")
    List<Student> findStudentsWithUser();
    @Query("SELECT s FROM Student s JOIN FETCH s.user u WHERE u.rollNo = :rollNo")
    Student findStudentWithUserByRollNo(@Param("rollNo") Long rollNo);
}

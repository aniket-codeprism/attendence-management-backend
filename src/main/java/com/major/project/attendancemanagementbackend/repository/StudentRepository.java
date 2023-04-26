package com.major.project.attendancemanagementbackend.repository;

import com.major.project.attendancemanagementbackend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}

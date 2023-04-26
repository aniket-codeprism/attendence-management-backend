package com.major.project.attendancemanagementbackend.repository;

import com.major.project.attendancemanagementbackend.entity.Admin;
import com.major.project.attendancemanagementbackend.entity.Institute;
import com.major.project.attendancemanagementbackend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByFirebaseId(String id);

}

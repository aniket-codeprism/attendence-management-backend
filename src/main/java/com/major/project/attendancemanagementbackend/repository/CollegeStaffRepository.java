package com.major.project.attendancemanagementbackend.repository;

import com.major.project.attendancemanagementbackend.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollegeStaffRepository extends JpaRepository<Staff, Long> {

    Optional<Staff> findByFirebaseId(String id);
}

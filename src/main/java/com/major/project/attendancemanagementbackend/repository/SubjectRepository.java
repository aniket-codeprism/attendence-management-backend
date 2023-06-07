package com.major.project.attendancemanagementbackend.repository;

import com.major.project.attendancemanagementbackend.entity.Course;
import com.major.project.attendancemanagementbackend.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findAllByCourse(Course course);
}

package com.major.project.attendancemanagementbackend.service;

import com.major.project.attendancemanagementbackend.entity.Course;
import com.major.project.attendancemanagementbackend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;


    public Course registerNewCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return  courseRepository.findAll();
    }
}

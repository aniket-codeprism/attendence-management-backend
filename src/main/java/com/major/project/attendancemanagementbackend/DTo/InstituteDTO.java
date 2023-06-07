package com.major.project.attendancemanagementbackend.DTo;

import com.major.project.attendancemanagementbackend.entity.Course;
import lombok.Data;

import java.util.List;

@Data
public class InstituteDTO {
    String name;
    Long id;
    List<CourseDTO> courses;
    Long collegeId;
    String email;
    String mobile;
    String address;
    String city;
    String postalCode;
}

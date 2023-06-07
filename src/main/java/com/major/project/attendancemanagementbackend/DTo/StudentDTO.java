package com.major.project.attendancemanagementbackend.DTo;

import com.major.project.attendancemanagementbackend.constants.Gender;
import com.major.project.attendancemanagementbackend.constants.Role;
import com.major.project.attendancemanagementbackend.entity.Course;
import com.major.project.attendancemanagementbackend.entity.Institute;
import com.major.project.attendancemanagementbackend.entity.Subject;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class StudentDTO {
    Long id;
    String name;
    InstituteDTO institute;
    String firebaseId;//
    Role authority;//
    String email;
    String mobile;
    String address;
    String city;
    String postalCode;
    OffsetDateTime dob;
    Gender gender;
    CourseDTO course;
    private Set<SubjectDTO> subjects = new HashSet<>();
}

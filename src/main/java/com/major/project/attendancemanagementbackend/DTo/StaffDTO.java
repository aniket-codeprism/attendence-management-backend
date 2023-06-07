package com.major.project.attendancemanagementbackend.DTo;

import com.major.project.attendancemanagementbackend.constants.Gender;
import com.major.project.attendancemanagementbackend.constants.Role;
import com.major.project.attendancemanagementbackend.entity.Course;
import com.major.project.attendancemanagementbackend.entity.Institute;
import com.major.project.attendancemanagementbackend.entity.Subject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class StaffDTO {

    Long id;
    String name;
    String firebaseId;//
    Role role;//
    String email;
    String mobile;
    String address;
    String city;
    String postalCode;
    OffsetDateTime dob;
    Gender gender;
    InstituteDTO institute;
     Set<CourseDTO> courses = new HashSet<>();
    Set<SubjectDTO> subjects=new HashSet<>();
}

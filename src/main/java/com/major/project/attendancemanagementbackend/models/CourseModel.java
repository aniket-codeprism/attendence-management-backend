package com.major.project.attendancemanagementbackend.models;

import com.major.project.attendancemanagementbackend.entity.Institute;
import lombok.Data;

@Data
public class CourseModel {
    String name;
    Institute institute;
}

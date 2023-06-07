package com.major.project.attendancemanagementbackend.models;

import com.major.project.attendancemanagementbackend.constants.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserModel {
    Long id;
    String name;
    Role roles;
    Long institute;
    Long courseId;
}

package com.major.project.attendancemanagementbackend.models;

import com.major.project.attendancemanagementbackend.constants.Role;
import lombok.Data;

import java.util.Set;

@Data

public class UserModel {
    String name;
    Set<Role> roles;
    Long institute;
    Long courseId;
}

package com.major.project.attendancemanagementbackend.models;

import lombok.Data;

@Data
public class InstituteModel {
    String name;
    Long collegeId;
    String email;
    String mobile;
    String address;
    String city;
    String postalCode;
}

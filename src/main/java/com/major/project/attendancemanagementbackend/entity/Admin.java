package com.major.project.attendancemanagementbackend.entity;

import com.major.project.attendancemanagementbackend.constants.Gender;
import com.major.project.attendancemanagementbackend.constants.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.time.OffsetDateTime;

@Entity@Table@Data

public class Admin {
    @Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column

    private Long id;//

    @Column
    String name;

    @Column(unique = true)
    String firebaseId;//
    @Column
    Role authority;//
    @Column(unique = true)
    String email;
    @Column
    String mobile;
    @Column
    String address;
    @Column
    String city;
    @Column
    String postalCode;
    @Column
    OffsetDateTime dob;
    @Column
    Gender gender;


}

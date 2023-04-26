package com.major.project.attendancemanagementbackend.entity;

import com.major.project.attendancemanagementbackend.constants.Role;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class CollegeStaff {
    @Id@Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(unique = true)
    String name;
    @Column
    Role role;

    @ManyToOne
    @JoinColumn
    Institute institute;
    @Column(unique = true)
    String email;
    @Column
    String mobile;
}

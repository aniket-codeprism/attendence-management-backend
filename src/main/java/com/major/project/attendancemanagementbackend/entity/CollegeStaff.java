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
    @Column
    String name;
    @Column
    Role roles;

    @ManyToOne
    @JoinColumn
    Institute institute;
    @Column
    String email;
    @Column
    String mobile;
}

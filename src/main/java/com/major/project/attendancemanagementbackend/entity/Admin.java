package com.major.project.attendancemanagementbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity@Table@Data
public class Admin {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    Long id;

    @Column
    String name;

    @Column
    String authority;
    @Column
    String email;
    @Column
    String mobile;
}

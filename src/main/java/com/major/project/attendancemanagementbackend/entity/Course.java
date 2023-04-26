package com.major.project.attendancemanagementbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Table@Entity@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    String name;
    @ManyToOne@JoinColumn
    Institute institute;

    @OneToMany(mappedBy = "course")
    Set<Student> students;

    @OneToOne @JoinColumn
    FingerprintDevice device;

}

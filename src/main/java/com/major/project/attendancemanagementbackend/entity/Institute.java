package com.major.project.attendancemanagementbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "institute")
public class Institute {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",unique = true)
    String name;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "institute",fetch = FetchType.EAGER,orphanRemoval = true)
    Set<Student> student=new HashSet<>();
    @Column(name = "collegeId")
    Long collegeId;
    @Column
    String email;
    @Column
    String mobile;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "institute")
    Set<CollegeStaff> staffs;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "institute")
    Set<Course> course;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "institute")
    Set<FingerprintDevice> devices;
}

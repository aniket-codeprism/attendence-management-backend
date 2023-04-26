package com.major.project.attendancemanagementbackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.major.project.attendancemanagementbackend.constants.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity

@Table(name = "institute")
public class Institute {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Getter
    private Long id;
    @Column(name = "name",unique = true)
            @Getter@Setter
    String name;
    @Column(name = "collegeId")
    @Getter@Setter

    Long collegeId;
    @Column(unique = true)
    @Getter@Setter

    String email;
    @Column
    @Getter@Setter

    String mobile;
    @Column
    @Getter@Setter

    Role role;

//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "institute",fetch = FetchType.EAGER,orphanRemoval = true)
//    Set<Student> student=new HashSet<>();
//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "institute",fetch = FetchType.EAGER,orphanRemoval = true)
//    Set<CollegeStaff> staffs;
//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "institute",fetch = FetchType.EAGER,orphanRemoval = true)
//    Set<Course> course;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "institute",fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonManagedReference
    @Getter@Setter
    Set<FingerprintDevice> devices=new HashSet<>();
}

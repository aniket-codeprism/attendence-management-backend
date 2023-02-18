package com.major.project.attendancemanagementbackend.entity;

import com.major.project.attendancemanagementbackend.constants.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Entity
@Table(name = "users")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;
    @Column(name = "name")
    String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "name",columnDefinition = "name")
    Institute institute;
    @Column
    String email;
    @Column
    String mobile;

    @ManyToOne
    @JoinColumn
    Course course;

}

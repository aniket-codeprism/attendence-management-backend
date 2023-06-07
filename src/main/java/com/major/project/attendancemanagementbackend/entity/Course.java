package com.major.project.attendancemanagementbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.major.project.attendancemanagementbackend.DTo.CourseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.Set;

@Table@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    @Getter@Setter

    private Long id;

    @Column(unique = true)    @Getter@Setter

    String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @Getter@Setter
    Institute institute;

    @OneToMany(mappedBy = "course")
    @Getter@Setter
    Set<Student> students;

    @OneToOne @JoinColumn
    @Getter@Setter
    FingerprintDevice device;
    @ManyToMany(mappedBy = "courses")
    @Getter@Setter
    private Set<Staff> staff = new HashSet<>();
    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY,orphanRemoval = true)
    @Getter
    @Setter
    Set<Subject> subjects=new HashSet<>();
    public CourseDTO toDto(){
        CourseDTO courseDTO=new CourseDTO();
        courseDTO.setId(id);
        courseDTO.setName(name);
        return courseDTO;
    }
}

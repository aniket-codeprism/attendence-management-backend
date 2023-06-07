package com.major.project.attendancemanagementbackend.entity;

import com.major.project.attendancemanagementbackend.DTo.SubjectDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subjects")

public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter@Setter
    private Long id;

    @Column(nullable = false)
    @Getter@Setter
    private String name;

    @ManyToMany(mappedBy = "subjects",fetch = FetchType.LAZY)
    @Getter@Setter
    private Set<Student> students = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @Getter@Setter
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter@Setter
    @JoinColumn(name = "staff")
    private Staff staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter@Setter
    @JoinColumn(name = "institute_id", nullable = false)
    private Institute institute;

    public SubjectDTO toDto(){
        SubjectDTO subjectDTO=new SubjectDTO();
        subjectDTO.setId(id);
        subjectDTO.setName(name);
        return subjectDTO;
    }
}

package com.major.project.attendancemanagementbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.major.project.attendancemanagementbackend.constants.Gender;
import com.major.project.attendancemanagementbackend.constants.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="staff")
public class Staff {
    @Id@Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(unique = true)
    String name;
    @Column(unique = true)
    String firebaseId;//
    @Column
    Role role;//
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

    @ManyToOne
    @JoinColumn(nullable = false)
    Institute institute;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "staff_course",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();
    @OneToMany(mappedBy = "staff",fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Set<Subject> subjects=new HashSet<>();
}

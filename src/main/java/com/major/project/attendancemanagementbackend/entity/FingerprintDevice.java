package com.major.project.attendancemanagementbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity@Table
public class FingerprintDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Getter
    private Long id;

    @Column
    @Getter@Setter
    boolean enabled=true;


    @PrimaryKeyJoinColumn
    @OneToOne
    Course course;
    @PrimaryKeyJoinColumn
    @OneToOne
    Admin admin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "name",columnDefinition = "name")
    @JsonBackReference
    @Getter@Setter
    Institute institute;


    public boolean getEnabled() {
        return enabled;
    }
}

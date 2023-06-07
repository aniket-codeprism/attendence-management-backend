package com.major.project.attendancemanagementbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity@Table
public class FingerprintDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @Getter
    private Long id;

    @Column
    @Getter@Setter
    boolean enabled=true;
    @Column
    @Getter@Setter
    String name="";


    @PrimaryKeyJoinColumn
    @OneToOne
    @Getter@Setter
    Course course;
    @PrimaryKeyJoinColumn
    @OneToOne
    @Getter@Setter
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

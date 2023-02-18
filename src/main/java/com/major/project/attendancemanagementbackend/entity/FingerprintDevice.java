package com.major.project.attendancemanagementbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data@Entity@Table
public class FingerprintDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique = true)
    String deviceId;

    @PrimaryKeyJoinColumn
    @OneToOne
    Course course;
    @ManyToOne@JoinColumn
    Institute institute;
}

package com.major.project.attendancemanagementbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.OffsetDateTime;

@Data
@Entity
@Table
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    Long id;
    @Column
    boolean verified=false;
    @Column
    String holidayDescription;
    @Column
    Date date;
    @Column
    Long studentId;
    @Column
    Long courseId;
    @Column
    Long subjectId;
    @Column
    Long instituteId;

}

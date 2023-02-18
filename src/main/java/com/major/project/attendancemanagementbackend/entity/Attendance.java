package com.major.project.attendancemanagementbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    Long id;

    @Column
    boolean holiday=false;
    @Column
    String holidayDescription;
    @Column
    Date date;

}

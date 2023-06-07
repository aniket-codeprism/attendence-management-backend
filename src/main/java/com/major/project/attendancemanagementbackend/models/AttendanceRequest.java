package com.major.project.attendancemanagementbackend.models;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Getter
public class AttendanceRequest {
    Long studentId;
    Long courseId;
    Long subjectId;
    Long instituteId;
    String startDate;
    String endDate;
    Boolean verfied;
}

package com.major.project.attendancemanagementbackend.models;

import com.major.project.attendancemanagementbackend.DTo.AttendanceDTO;
import com.major.project.attendancemanagementbackend.entity.Attendance;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;

@Data
public class AttendanceReponseModel {
    List<String> dates = new ArrayList<>();
    List<HashMap<String,List<AttendanceDTO>>> attendaces=new ArrayList<>();
}

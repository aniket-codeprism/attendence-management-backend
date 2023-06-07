package com.major.project.attendancemanagementbackend.controller;


import com.major.project.attendancemanagementbackend.DTo.StaffDTO;
import com.major.project.attendancemanagementbackend.entity.*;
import com.major.project.attendancemanagementbackend.models.AttendanceRequest;
import com.major.project.attendancemanagementbackend.models.LoginModel;
import com.major.project.attendancemanagementbackend.models.UserModel;
import com.major.project.attendancemanagementbackend.models.VerifyList;
import com.major.project.attendancemanagementbackend.service.CollegeStaffService;
import com.major.project.attendancemanagementbackend.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/staff", consumes="application/json")
public class StaffController {
    @Autowired
    CollegeStaffService staffService;
    @Autowired
    InstituteService instituteService;

    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody LoginModel loginModel) {
        try {
            return ResponseEntity.ok(staffService.login(loginModel.getFirebaseId()));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }
    public void registerStudent(){

    }
    @PostMapping("/getAttendance")
    public ResponseEntity getAttendance(@RequestBody AttendanceRequest attendanceRequest ) {
        try{
            return ResponseEntity.ok(instituteService.getAttendance(attendanceRequest));
        }catch (Exception e) { e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }@PostMapping("/verifyAttendance")
    public ResponseEntity verifyAttendance(@RequestBody VerifyList attendanceRequest ) {
        try{
            return ResponseEntity.ok(instituteService.verifyAttendance(attendanceRequest));
        }catch (Exception e) { e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

package com.major.project.attendancemanagementbackend.controller;


import com.major.project.attendancemanagementbackend.DTo.StaffDTO;
import com.major.project.attendancemanagementbackend.entity.*;
import com.major.project.attendancemanagementbackend.models.AttendanceRequest;
import com.major.project.attendancemanagementbackend.models.LoginModel;
import com.major.project.attendancemanagementbackend.models.UserModel;
import com.major.project.attendancemanagementbackend.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/institute", consumes="application/json")
public class InstituteController {
    @Autowired
    InstituteService instituteService;

    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody LoginModel loginModel) {
        try {
            return ResponseEntity.ok(instituteService.login(loginModel.getFirebaseId()));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }
    public void registerStudent(){

    }
    @PostMapping("/createNewStaff")
    public ResponseEntity createNewStaff(@RequestBody Staff staff) {
        try {
            System.out.println(staff.toString());
        return ResponseEntity.ok(instituteService.createNewStaff(staff));
        } catch (Exception e) { e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }
    @PostMapping("/getAllStaffs")
    public ResponseEntity getAllStaffs(@RequestBody UserModel userModel) {
        try{
        return ResponseEntity.ok(instituteService.getAllStaffs(userModel));
    } catch (Exception e) { e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }
    }
    @PostMapping("/registerNewCourse")
    public ResponseEntity registerNewCourse(@RequestBody Course course) {
        try{
        return ResponseEntity.ok(instituteService.registerNewCourse(course));
    } catch (Exception e) { e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }
    }
    @PostMapping("/getAllCourses")
    public ResponseEntity getAllCourses(@RequestBody UserModel userModel) {
        try{
        return ResponseEntity.ok(instituteService.getAllCourses(userModel));
    } catch (Exception e) { e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }
    }
    @PostMapping("/registerNewStudent")
    public ResponseEntity registerNewStudent(@RequestBody Student student) {
        try{
            return ResponseEntity.ok(instituteService.registerNewStudent(student));
        }catch (Exception e) { e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }
    @PostMapping("/createNewSubject")
    public ResponseEntity registerNewSubject(@RequestBody Subject student) {
        try{

            return ResponseEntity.ok(instituteService.registerNewSubject(student));
        }catch (Exception e) { e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/getAllSubjects")
    public ResponseEntity getAllSubjects(@RequestBody UserModel userModel) {
        try{

            return ResponseEntity.ok(instituteService.getAllSubjects(userModel));
        }catch (Exception e) { e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/getAttendaceBySubject")
    public ResponseEntity getAttendaceBySubject(@RequestBody AttendanceRequest attendanceRequest ) {
        try{

            return ResponseEntity.ok(instituteService.getAttendaceBySubject(attendanceRequest));
        }catch (Exception e) { e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/getInstituteWithCoursesAndSubjects")
    public ResponseEntity getAttendaceBySubject(@RequestBody UserModel userModel ) {
        try{

            return ResponseEntity.ok(instituteService.getInstituteWithCoursesAndSubjects(userModel.getId()));
        }catch (Exception e) { e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/getAttendance")
    public ResponseEntity getAttendance(@RequestBody AttendanceRequest attendanceRequest ) {
        try{
            return ResponseEntity.ok(instituteService.getAttendance(attendanceRequest));
        }catch (Exception e) { e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

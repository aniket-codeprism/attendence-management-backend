package com.major.project.attendancemanagementbackend.controller;

import com.major.project.attendancemanagementbackend.DTo.StudentDTO;
import com.major.project.attendancemanagementbackend.entity.FingerprintDevice;
import com.major.project.attendancemanagementbackend.entity.Student;
import com.major.project.attendancemanagementbackend.models.LoginModel;
import com.major.project.attendancemanagementbackend.models.UserModel;
import com.major.project.attendancemanagementbackend.service.FingerprintDeviceService;
import com.major.project.attendancemanagementbackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    FingerprintDeviceService fingerprintDeviceService;

    @PostMapping("/loginStudent")
    public ResponseEntity<StudentDTO> loginStudent(@RequestBody LoginModel loginModel){
        try {

            return ResponseEntity.ok(studentService.loginStudent(loginModel.getFirebaseId()));

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(){
        try {

            return ResponseEntity.ok(studentService.getAll());

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

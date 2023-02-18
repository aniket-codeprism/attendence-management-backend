package com.major.project.attendancemanagementbackend.controller;

import com.major.project.attendancemanagementbackend.entity.Student;
import com.major.project.attendancemanagementbackend.models.UserModel;
import com.major.project.attendancemanagementbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/student")
public class StudentController {
    @Autowired
    UserService userService;
    @PostMapping(path = "/register")
    public ResponseEntity registerUser(@RequestBody UserModel user){
        Student users = userService.registerUser(user);
        return ResponseEntity.ok(users.getInstitute().getStudent().size());
    }
    public void login(){}
}

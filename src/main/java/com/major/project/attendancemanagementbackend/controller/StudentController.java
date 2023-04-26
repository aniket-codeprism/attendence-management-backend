package com.major.project.attendancemanagementbackend.controller;

import com.major.project.attendancemanagementbackend.entity.Student;
import com.major.project.attendancemanagementbackend.service.StudentService;
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
    StudentService studentService;
    @PostMapping("/registerStudent")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student){
        try {
            return ResponseEntity.ok(studentService.registerStudent(student));

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/getStudentById")
    public ResponseEntity<Student> getStudentById(@RequestBody Student student){
        try {
            return ResponseEntity.ok(studentService.getStudentById(student.getId()));

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

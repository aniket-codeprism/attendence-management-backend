package com.major.project.attendancemanagementbackend.controller;

import com.major.project.attendancemanagementbackend.entity.FingerprintDevice;
import com.major.project.attendancemanagementbackend.entity.Student;
import com.major.project.attendancemanagementbackend.models.DeviceVerify;
import com.major.project.attendancemanagementbackend.models.FingerprintMessageModel;
import com.major.project.attendancemanagementbackend.service.FingerprintDeviceService;
import com.major.project.attendancemanagementbackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/fingerprint")
public class FingerprintController {
    @Autowired
    FingerprintDeviceService fingerprintDeviceService;
@Autowired
    StudentService studentService;

    @GetMapping(path="/registerDevice")
    public ResponseEntity<FingerprintDevice> registerDevice(){
        try {
        return ResponseEntity.ok(fingerprintDeviceService.createFingerprintDevice());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<Object> addAttendance(Long id){
        try {


        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return null;
    }
    public ResponseEntity<Object> getDeviceID(){
        try {


        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return null;
    }
    @PostMapping("/verify")
    public ResponseEntity<Object> verify(@RequestBody DeviceVerify deviceVerify){
        try {
            return ResponseEntity.ok( fingerprintDeviceService.verify(deviceVerify.getId()));

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
//    public ResponseEntity<List<FingerprintDevice>> getAllDevicesByInstitute(){
//        try {
//           return ResponseEntity.ok( fingerprintDeviceService.getAllDevicesByInstitute());
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
@PostMapping("/sendMessage")
public ResponseEntity<Object> sendMessage(@RequestBody FingerprintMessageModel fingerprintMessageModel){
    try {
        fingerprintDeviceService.sendMessageToDeviceId(fingerprintMessageModel.getId(), fingerprintMessageModel.getMessage());
        return ResponseEntity.ok(new Student());

    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
}
@PostMapping("/clearDevice")
    public ResponseEntity<Object> clearDevice(@RequestBody FingerprintMessageModel fingerprintMessageModel){
        try {
            fingerprintDeviceService.sendMessageToDeviceId(fingerprintMessageModel.getId(), "clear.0.clear");
            return ResponseEntity.ok(new Student());

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/getStudent")
    public ResponseEntity<Student> getStudentById(@RequestBody Student student){
        try {
            return ResponseEntity.ok(studentService.getStudentById(student.getId()));

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

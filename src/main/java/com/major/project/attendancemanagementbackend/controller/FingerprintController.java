package com.major.project.attendancemanagementbackend.controller;

import com.major.project.attendancemanagementbackend.DTo.StudentDTO;
import com.major.project.attendancemanagementbackend.entity.Attendance;
import com.major.project.attendancemanagementbackend.entity.FingerprintDevice;
import com.major.project.attendancemanagementbackend.entity.Student;
import com.major.project.attendancemanagementbackend.models.DeviceVerify;
import com.major.project.attendancemanagementbackend.models.FingerprintMessageModel;
import com.major.project.attendancemanagementbackend.models.UserModel;
import com.major.project.attendancemanagementbackend.service.FingerprintDeviceService;
import com.major.project.attendancemanagementbackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/addAttendance")
    public ResponseEntity<List<Attendance>> addAttendance(@RequestBody  UserModel userModel){
        try {

            return ResponseEntity.ok(fingerprintDeviceService.registerAttendance(userModel));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
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
            return ResponseEntity.ok("done");

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/getStudent")
    public ResponseEntity<StudentDTO> getStudentById(@RequestBody Student student){
        try {
            UserModel userModel=new UserModel();
            userModel.setId(student.getId());
            fingerprintDeviceService.registerAttendance(userModel);
            return ResponseEntity.ok(studentService.getStudentById(student.getId()));

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

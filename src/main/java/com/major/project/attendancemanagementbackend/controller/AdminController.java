package com.major.project.attendancemanagementbackend.controller;

import com.major.project.attendancemanagementbackend.entity.Admin;
import com.major.project.attendancemanagementbackend.entity.FingerprintDevice;
import com.major.project.attendancemanagementbackend.entity.Institute;
import com.major.project.attendancemanagementbackend.models.AddDeviceModel;
import com.major.project.attendancemanagementbackend.models.InstituteModel;
import com.major.project.attendancemanagementbackend.models.LoginModel;
import com.major.project.attendancemanagementbackend.service.AdminService;
import com.major.project.attendancemanagementbackend.service.FingerprintDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")

public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    FingerprintDeviceService fingerprintDeviceService;

    @PostMapping(path = "/registerInstitute")
    public ResponseEntity registerInstitute(@RequestBody InstituteModel institute) {
        try {
            Institute institute1 =
                    adminService.registerNewInstitute(institute);
            return ResponseEntity.ok(institute1);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping(path = "/getAllInstitutes")
    public ResponseEntity getAllInstitutes() {
        try {


            return ResponseEntity.ok(adminService.getAllInstitutes());

        } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody LoginModel loginModel) {
        try {
            Admin register = adminService.login(loginModel.getFirebaseId());
            return ResponseEntity.ok(register);

        } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }
    @PostMapping(path = "/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody LoginModel loginModel) {
        try {
            return ResponseEntity.ok(adminService.resetPassword(loginModel.getEmail()));

        } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }
    @PostMapping(path = "/registerNewAdmin")
    public ResponseEntity registerNewAdmin(@RequestBody Admin admin) {
        try {
            return ResponseEntity.ok(adminService.registerNewAdmin(admin));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping(path = "/getAdminList")
    public ResponseEntity getAllAdmin() {
        try {
            return ResponseEntity.ok(adminService.getAllAdmin());

        } catch (Exception e) {
            System.out.println(e.getClass());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check if Email is already Present or Not ->" + e.getMessage());
        }

    }
    @GetMapping(path = "/deleteAll")
    public ResponseEntity deleteAll() {
        try {
            return ResponseEntity.ok(adminService.deleteAll());

        } catch (Exception e) {
            System.out.println(e.getClass());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check if Email is already Present or Not ->" + e.getMessage());
        }

    }
    @GetMapping(path = "/getAllDevices")
    public ResponseEntity getAllDevices() {
        try {
            return ResponseEntity.ok(fingerprintDeviceService.getAllDevices());

        } catch (Exception e) {
            System.out.println(e.getClass());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check if Email is already Present or Not ->" + e.getMessage());
        }

    }
    @PostMapping("/addDevice")
    public FingerprintDevice addDevice(@RequestBody AddDeviceModel addDeviceModel){
        return adminService.addDevice(addDeviceModel.getDeviceId(), addDeviceModel.getId());
    }
}

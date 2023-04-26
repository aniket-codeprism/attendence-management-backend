package com.major.project.attendancemanagementbackend.controller;

import com.major.project.attendancemanagementbackend.entity.Admin;
import com.major.project.attendancemanagementbackend.entity.Institute;
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
    public ResponseEntity<Institute> registerInstitute(@RequestBody InstituteModel institute) {
        try {
            Institute institute1 =
                    adminService.registerNewInstitute(institute);
            return ResponseEntity.ok(institute1);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Admin> login(@RequestBody LoginModel loginModel) {
        try {
            Admin register = adminService.login(loginModel.getFirebaseId());
            return ResponseEntity.ok(register);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    //    @PostMapping(path="/register")
//    public ResponseEntity<Admin> register(@RequestBody Admin admin){
//        Admin register = adminService.register(admin);
//        return ResponseEntity.ok(register);
//
//    }
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
}

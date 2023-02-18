package com.major.project.attendancemanagementbackend.controller;

import com.major.project.attendancemanagementbackend.entity.Institute;
import com.major.project.attendancemanagementbackend.models.InstituteModel;
import com.major.project.attendancemanagementbackend.service.InstituteService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin")

public class AdminController {
    @Autowired
    InstituteService instituteService;
    @PostMapping(path = "/registerInstitute")
    public ResponseEntity<Institute> registerInstitute(@RequestBody InstituteModel institute)
    {
        Institute institute1 = instituteService.registerInstitute(institute);
        return ResponseEntity.ok(institute1);
    }
    public void registerDevice(){

    }

    public void login(){

    }

}

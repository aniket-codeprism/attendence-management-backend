package com.major.project.attendancemanagementbackend.controller;


import com.major.project.attendancemanagementbackend.entity.FingerprintDevice;
import com.major.project.attendancemanagementbackend.models.AddDeviceModel;
import com.major.project.attendancemanagementbackend.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(path = "/institute")
public class InstituteController {
    @Autowired
    InstituteService instituteService;

    public void login(){

    }
    public void registerStudent(){

    }
    @PostMapping("/addDevice")
    public Set<FingerprintDevice> addDevice(@RequestBody AddDeviceModel addDeviceModel){
        return instituteService.addDevice(addDeviceModel.getDeviceId(), addDeviceModel.getId());
    }

}

package com.major.project.attendancemanagementbackend.service;

import com.major.project.attendancemanagementbackend.entity.Institute;
import com.major.project.attendancemanagementbackend.models.InstituteModel;
import com.major.project.attendancemanagementbackend.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstituteService {
    @Autowired
    InstituteRepository instituteRepository;
    public Institute registerInstitute(InstituteModel instituteModel){
        Institute institute=new Institute();
        institute.setName(instituteModel.getName());
        institute.setCollegeId(instituteModel.getCollegeId());
        return instituteRepository.save(institute);
    }
}

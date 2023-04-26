package com.major.project.attendancemanagementbackend.service;

import com.major.project.attendancemanagementbackend.entity.FingerprintDevice;
import com.major.project.attendancemanagementbackend.entity.Institute;
import com.major.project.attendancemanagementbackend.models.InstituteModel;
import com.major.project.attendancemanagementbackend.repository.InstituteRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InstituteService {
    @Autowired
    InstituteRepository instituteRepository;
    @Autowired
    FingerprintDeviceService fingerprintDeviceService;
    public Institute registerInstitute(InstituteModel instituteModel){
        Institute institute=new Institute();
        institute.setName(instituteModel.getName());
        institute.setCollegeId(instituteModel.getCollegeId());
        return instituteRepository.save(institute);
    }
    public List<Institute> getAllInstitutes() {
        return instituteRepository.findAll();
    }

    public Optional<Institute> getInstituteById(Long id) {
        return instituteRepository.findById(id);
    }

    public Optional<Institute> getInstituteByName(String name) {
        return instituteRepository.findByName(name);
    }
    public Institute updateInstitute(Long id, Institute updatedInstitute) {
        Optional<Institute> existingInstitute = instituteRepository.findById(id);
        if (existingInstitute.isPresent()) {
            Institute institute = existingInstitute.get();
            if(updatedInstitute.getName()!=null)
            institute.setName(updatedInstitute.getName());
            if(updatedInstitute.getCollegeId()!=null)
            institute.setCollegeId(updatedInstitute.getCollegeId());
            if(updatedInstitute.getEmail()!=null)
            institute.setEmail(updatedInstitute.getEmail());
            if(updatedInstitute.getMobile()!=null)
            institute.setMobile(updatedInstitute.getMobile());
//            if(updatedInstitute.getStudent()!=null)
//            institute.setStudent(updatedInstitute.getStudent());
//            if(updatedInstitute.getStaffs()!=null)
//            institute.setStaffs(updatedInstitute.getStaffs());
//            if(updatedInstitute.getCourse()!=null)
//            institute.setCourse(updatedInstitute.getCourse());
//            if(updatedInstitute.getDevices()!=null)
//            institute.setDevices(updatedInstitute.getDevices());
            if(updatedInstitute.getRole()!=null)
                institute.setRole(updatedInstitute.getRole());
            return instituteRepository.save(institute);
        }
        return null;
    }

    public Set<FingerprintDevice> addDevice(Long id, Long institute){
        Optional<Institute> byId = getInstituteById(institute);
        if(byId.isEmpty()) return null;
        Optional<FingerprintDevice> byId1 = fingerprintDeviceService.findById(id);
        if(byId1.isEmpty()) {
            return null;
        }
        Institute institute1 = byId.get();
        FingerprintDevice fingerprintDevice = byId1.get();
        fingerprintDevice.setInstitute(institute1);
//        fingerprintDeviceService.save(fingerprintDevice);
        institute1.getDevices().add(fingerprintDevice);
        instituteRepository.save(institute1);
        Institute institute2 = instituteRepository.findById(institute).get();
        System.out.println(
                institute2.getName());;
        return institute2.getDevices();

//        return  null;
    }

}

package com.major.project.attendancemanagementbackend.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.major.project.attendancemanagementbackend.constants.Role;
import com.major.project.attendancemanagementbackend.entity.Admin;
import com.major.project.attendancemanagementbackend.entity.FingerprintDevice;
import com.major.project.attendancemanagementbackend.entity.Institute;
import com.major.project.attendancemanagementbackend.exceptionss.Exceptions;
import com.major.project.attendancemanagementbackend.models.InstituteModel;
import com.major.project.attendancemanagementbackend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    FirebaseService firebaseService;

    @Autowired
    InstituteService instituteService;
    public Admin login(String firebaseId) throws Exception {
        Optional<Admin> byFirebaseId = adminRepository.findByFirebaseId(firebaseId);
        if (byFirebaseId.isEmpty()) {
            throw new Exception("userNotFound");
        }
        return byFirebaseId.get();
    }

    public Admin registerNewAdmin(Admin adminRequest) throws Exception {
        Optional<Admin> admin = adminRepository.findByFirebaseId(adminRequest.getFirebaseId());
        System.out.println(adminRequest.getFirebaseId());
        System.out.println(adminRequest.getName());
        if (admin.isEmpty()) {
            throw Exceptions.adminNotFoundException;
        }
        UserRecord user = firebaseService.signUpUser(adminRequest.getEmail());
        Admin newAdmin=new Admin();
        newAdmin.setEmail(adminRequest.getEmail());
        newAdmin.setName(adminRequest.getName());
        newAdmin.setMobile(adminRequest.getMobile());
        newAdmin.setFirebaseId(user.getUid());
        newAdmin.setAuthority(Role.ADMIN);
        newAdmin.setAddress(adminRequest.getAddress());
        newAdmin.setCity(adminRequest.getCity());
        newAdmin.setDob(adminRequest.getDob());
        newAdmin.setGender(adminRequest.getGender());
        newAdmin.setPostalCode(adminRequest.getPostalCode());
        FirebaseAuth.getInstance().deleteUser(user.getUid());
        return adminRepository.save(newAdmin);

    }

    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    public Institute registerNewInstitute(InstituteModel institute) throws FirebaseAuthException {
        return instituteService.registerInstitute(institute);
    }
    public List<Institute> getAllInstitutes() {
        return instituteService.getAllInstitutes();
    }

    public Object deleteAll() throws FirebaseAuthException {
        return firebaseService.clearFirebase();
    }

    public String resetPassword(String firebaseId) throws FirebaseAuthException {
        return firebaseService.resetPassword(firebaseId);
    }

    public FingerprintDevice addDevice(Long deviceId, Long id) {
        return instituteService.addDevice(deviceId, id);
    }
}

package com.major.project.attendancemanagementbackend.service;

import com.major.project.attendancemanagementbackend.entity.Institute;
import com.major.project.attendancemanagementbackend.entity.Student;
import com.major.project.attendancemanagementbackend.models.UserModel;
import com.major.project.attendancemanagementbackend.repository.InstituteRepository;
import com.major.project.attendancemanagementbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    InstituteRepository instituteRepository;
    public Student registerUser(UserModel userModel){
        Student user=new Student();
        user.setName(userModel.getName());
        Optional<Institute> institute = instituteRepository.findById(userModel.getInstitute());
        if(institute.isPresent()){
            user.setInstitute(institute.get());
            System.out.println(instituteRepository.findById(1L).get().getStudent().toArray().length);
        }
        return userRepository.save(user);
    }
}

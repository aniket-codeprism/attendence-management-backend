package com.major.project.attendancemanagementbackend.service;

import com.major.project.attendancemanagementbackend.entity.Student;
import com.major.project.attendancemanagementbackend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    FingerprintDeviceService fingerprintDeviceService;
    public Student registerStudent(Student student){
        Student save = studentRepository.save(student);
        fingerprintDeviceService.sendMessageToDeviceId(save.getId(), "register."+save.getId()+"."+save.getName());
        return save;
    }

    public Student getStudentById(Long id) {
        Optional<Student> byId = studentRepository.findById(id);
        if(byId.isEmpty())throw new RuntimeException("Not found");
        return  byId.get();

    }
}

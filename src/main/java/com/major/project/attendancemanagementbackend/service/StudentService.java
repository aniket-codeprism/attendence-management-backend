package com.major.project.attendancemanagementbackend.service;

import com.major.project.attendancemanagementbackend.DTo.StudentDTO;
import com.major.project.attendancemanagementbackend.entity.Student;
import com.major.project.attendancemanagementbackend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public StudentDTO getStudentById(Long id) {
        Optional<Student> byId = studentRepository.findById(id);
        if(byId.isEmpty())throw new RuntimeException("Not found");
        StudentDTO studentDTO=new StudentDTO();
        studentDTO.setId(byId.get().getId());
        studentDTO.setName(byId.get().getName());
        return studentDTO;

    }
    public StudentDTO loginStudent(String id) {
        Optional<Student> byId = studentRepository.findByFirebaseId(id);
        if(byId.isEmpty())throw new RuntimeException("Not found");
        StudentDTO studentDTO=new StudentDTO();
        studentDTO.setId(byId.get().getId());
        studentDTO.setName(byId.get().getName());
        studentDTO.setCity(byId.get().getCity());
        studentDTO.setAddress(byId.get().getAddress());
        studentDTO.setDob(byId.get().getDob());
        studentDTO.setAuthority(byId.get().getAuthority());
        studentDTO.setGender(byId.get().getGender());
        studentDTO.setFirebaseId(byId.get().getFirebaseId());
        studentDTO.setEmail(byId.get().getEmail());
        studentDTO.setMobile(byId.get().getMobile());
        studentDTO.setPostalCode(byId.get().getPostalCode());
        studentDTO.setInstitute(byId.get().getInstitute().toDto());
        studentDTO.setCourse(byId.get().getCourse().toDto());
        for(var sub:byId.get().getSubjects()){
            studentDTO.getSubjects().add(sub.toDto());
        }
        return studentDTO;

    }

    public List<StudentDTO> getAll() {
        List<Student> all = studentRepository.findAll();
        List<StudentDTO> dtos = new ArrayList<>();
        for(var student:all){
            StudentDTO studentDTO=new StudentDTO();
            studentDTO.setId(student.getId());
            studentDTO.setName(student.getName());
            studentDTO.setCity(student.getCity());
            studentDTO.setAddress(student.getAddress());
            studentDTO.setDob(student.getDob());
            studentDTO.setAuthority(student.getAuthority());
            studentDTO.setGender(student.getGender());
            studentDTO.setFirebaseId(student.getFirebaseId());
            studentDTO.setEmail(student.getEmail());
            studentDTO.setMobile(student.getMobile());
            studentDTO.setPostalCode(student.getPostalCode());
            studentDTO.setInstitute(student.getInstitute().toDto());
            studentDTO.setCourse(student.getCourse().toDto());
            dtos.add(studentDTO);
        }
        return dtos;
    }
}

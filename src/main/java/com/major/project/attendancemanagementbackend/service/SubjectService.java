package com.major.project.attendancemanagementbackend.service;

import com.google.gson.Gson;
import com.major.project.attendancemanagementbackend.entity.Subject;
import com.major.project.attendancemanagementbackend.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject saveSubject(Subject subject) {
        System.out.println(subject.toString());
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + id));
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    // Add more methods as needed for specific business logic
}

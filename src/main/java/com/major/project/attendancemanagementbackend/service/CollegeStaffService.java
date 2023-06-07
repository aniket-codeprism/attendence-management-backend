package com.major.project.attendancemanagementbackend.service;

import com.major.project.attendancemanagementbackend.DTo.CourseDTO;
import com.major.project.attendancemanagementbackend.DTo.StaffDTO;
import com.major.project.attendancemanagementbackend.entity.Staff;
import com.major.project.attendancemanagementbackend.repository.CollegeStaffRepository;
import com.major.project.attendancemanagementbackend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollegeStaffService {

    private final CollegeStaffRepository collegeStaffRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    public CollegeStaffService(CollegeStaffRepository collegeStaffRepository) {
        this.collegeStaffRepository = collegeStaffRepository;
    }

    public List<Staff> getAllCollegeStaffs() {
        return collegeStaffRepository.findAll();
    }

    public Optional<Staff> getCollegeStaffById(Long id) {
        return collegeStaffRepository.findById(id);
    }

    public Staff createNewStaff(Staff staff) {
        return collegeStaffRepository.save(staff);
    }

    public void deleteCollegeStaff(Long id) {
        collegeStaffRepository.deleteById(id);
    }

    public StaffDTO login(String firebaseId) {
        Optional<Staff> byFirebaseId = collegeStaffRepository.findByFirebaseId(firebaseId);
        if(byFirebaseId.isEmpty()){
            throw new RuntimeException("Staff not found");
        }
        Staff staff = byFirebaseId.get();
        StaffDTO staffDTO=new StaffDTO();
        staffDTO.setFirebaseId(staff.getFirebaseId());
        staffDTO.setCity(staff.getCity());
        staffDTO.setRole(staff.getRole());
        staffDTO.setEmail(staff.getEmail());
        staffDTO.setMobile(staff.getMobile());
        staffDTO.setAddress(staff.getAddress());
        staffDTO.setDob(staff.getDob());
        staffDTO.setId(staff.getId());
        staffDTO.setGender(staff.getGender());
        System.out.println(staff.getSubjects().size());

        for(var course:staff.getCourses()){
            CourseDTO courseDTO=new CourseDTO();
            courseDTO.setName(course.getName());
            courseDTO.setId(course.getId());
            for(var subject:course.getSubjects()){
                courseDTO.getSubjects().add(subject.toDto());
            }
            staffDTO.getCourses().add(courseDTO);


        }
        staffDTO.setInstitute(staff.getInstitute().toDto());
        return staffDTO;

    }
}

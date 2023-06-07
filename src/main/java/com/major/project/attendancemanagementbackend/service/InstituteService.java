package com.major.project.attendancemanagementbackend.service;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.major.project.attendancemanagementbackend.DTo.*;
import com.major.project.attendancemanagementbackend.constants.Role;
import com.major.project.attendancemanagementbackend.entity.*;
import com.major.project.attendancemanagementbackend.models.*;
import com.major.project.attendancemanagementbackend.repository.InstituteRepository;
import com.major.project.attendancemanagementbackend.repository.StudentRepository;
import com.major.project.attendancemanagementbackend.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InstituteService {
    @Autowired
    InstituteRepository instituteRepository;
    @Autowired
    CollegeStaffService collegeStaffService;
    @Autowired
    FingerprintDeviceService fingerprintDeviceService;

    @Autowired
    FirebaseService firebaseService;
    @Autowired
    CourseService courseService;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    StudentRepository studentRepository;
    public Institute registerInstitute(InstituteModel instituteModel) throws FirebaseAuthException {
        Institute institute=new Institute();
        institute.setName(instituteModel.getName());
        institute.setCollegeId(instituteModel.getCollegeId());
        institute.setEmail(instituteModel.getEmail());
        institute.setRole(Role.INSTITUTE);
        institute.setMobile(instituteModel.getMobile());
        institute.setAddress(instituteModel.getAddress());
        institute.setCity(instituteModel.getCity());
        institute.setPostalCode(instituteModel.getPostalCode());
        institute.setFirebaseId(firebaseService.signUpUser(instituteModel.getEmail()).getUid());
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

    public FingerprintDevice addDevice(Long id, Long institute){
        Optional<Institute> byId = getInstituteById(institute);
        if(byId.isEmpty()) throw new RuntimeException("Institue Not Found");
        Optional<FingerprintDevice> byId1 = fingerprintDeviceService.findById(id);
        if(byId1.isEmpty()) {
            throw new RuntimeException("Device Not Found");
        }
        Institute institute1 = byId.get();
        FingerprintDevice fingerprintDevice = byId1.get();
        fingerprintDevice.setName(fingerprintDevice.getName()+institute1.getName());
        fingerprintDevice.setInstitute(institute1);
        FingerprintDevice save = fingerprintDeviceService.save(fingerprintDevice);
        institute1.getDevices().add(fingerprintDevice);
        System.out.println(instituteRepository.save(institute1).getDevices().size());
        return save;

//        return  null;
    }

    public Staff createNewStaff(Staff staff) throws FirebaseAuthException {
        UserRecord userRecord = firebaseService.signUpUser(staff.getEmail());
        staff.setFirebaseId(userRecord.getUid());
        return collegeStaffService.createNewStaff(staff);
    }

    public List<StaffDTO> getAllStaffs(UserModel userModel) {
        List<Staff> allStaffs = instituteRepository.findById(userModel.getId()).get().getStaff().stream().toList();
        List<StaffDTO> staffDTOS=new ArrayList<>();
        for(var staff:allStaffs){
            StaffDTO staffDTO=new StaffDTO();
            staffDTO.setId(staff.getId());
            staffDTO.setName(staff.getName());
            staffDTO.setDob(staff.getDob());
//            staffDTO.setCourses(staff.getCourses());
            staffDTO.setGender(staff.getGender());
            staffDTO.setRole(staff.getRole());
//            staffDTO.setSubjects(staff.getSubjects());
            staffDTO.setCity(staff.getCity());
            staffDTO.setAddress(staff.getAddress());
            staffDTO.setEmail(staff.getEmail());
            staffDTO.setMobile(staff.getMobile());
            staffDTO.setPostalCode(staff.getPostalCode());
            staffDTO.setFirebaseId(staff.getFirebaseId());
            staffDTOS.add(staffDTO);
        }
        return staffDTOS;
    }

    public Course registerNewCourse(Course course) {
       return courseService.registerNewCourse(course);
    }

    public List<CourseDTO> getAllCourses(UserModel userModel) {
        List<Course> courses = instituteRepository.findById(userModel.getId()).get().getCourses().stream().toList();
        List<CourseDTO> courseDTOS= new ArrayList<>();
        for(var course:courses){
            List<SubjectDTO> subjects=new ArrayList<>();
            for(var subject:course.getSubjects()){
                SubjectDTO subjectDTO=new SubjectDTO();
                subjectDTO.setName(subject.getName());
                subjectDTO.setId(subject.getId());
                subjects.add(subjectDTO);
                System.out.println(subject.getId());
            }
            CourseDTO courseDTO=new CourseDTO();
            courseDTO.setName(course.getName());
            courseDTO.setId(course.getId());
            courseDTO.setSubjects(subjects);
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

    public Student registerNewStudent(Student student) throws FirebaseAuthException {
        UserRecord userRecord = firebaseService.signUpUser(student.getEmail());
        student.setFirebaseId(userRecord.getUid());

        Student save = studentRepository.save(student);
        fingerprintDeviceService.sendMessageToDeviceId(student.getDeviceId(), "register."+save.getId()+"."+save.getName());
        return save;
    }

    public InstituteDTO login(String firebaseId) throws Exception {
        Optional<Institute> byFirebaseId = instituteRepository.findByFirebaseId(firebaseId);
        if (byFirebaseId.isEmpty()) {
            System.out.println(firebaseId);
            throw new Exception("userNotFound");
        }
        Institute institute = byFirebaseId.get();
        InstituteDTO dto=new InstituteDTO();
        return byFirebaseId.get().toDto();
    }
    public Subject registerNewSubject(Subject subject){
        return subjectRepository.save(subject);
    }

    public AttendanceReponseModel getAttendaceBySubject(AttendanceRequest attendanceRequest) throws ParseException {
        return fingerprintDeviceService.getAttendanceBySubject(attendanceRequest);
    }

    public List<SubjectDTO> getAllSubjects(UserModel userModel) {
        List<SubjectDTO> subjectDTOS=new ArrayList<>();
        List<Subject> allByCourse = subjectRepository.findAllByCourse(courseService.courseRepository.findById(userModel.getId()).get());
        for(var subject:allByCourse){
            SubjectDTO subjectDTO=new SubjectDTO();
            subjectDTO.setId(subject.getId());
            subjectDTO.setName(subject.getName());
            subjectDTOS.add(subjectDTO);
        }
        return  subjectDTOS;
    }
    public InstituteDTO getInstituteWithCoursesAndSubjects(Long id){
        Optional<Institute> byId = instituteRepository.findById(id);
        if(byId.isPresent()){
            Institute institute = byId.get();
            Set<Course> courses = institute.getCourses();
            List<CourseDTO> courseDTOS=new ArrayList<>();
            for(var course:courses){
                Set<Subject> subjects = course.getSubjects();
                List<SubjectDTO> subjectDTOS=new ArrayList<>();
                CourseDTO courseDTO=new CourseDTO();
                for(var subject:subjects){
                    SubjectDTO subjectDTO=new SubjectDTO();
                    subjectDTO.setName(subject.getName());
                    subjectDTO.setId(subject.getId());
                    subjectDTOS.add(subjectDTO);
                }
                courseDTO.setId(course.getId());
                courseDTO.setName(course.getName());
                courseDTO.setSubjects(subjectDTOS);
                List<StudentDTO> studentDTOS=new ArrayList<>();

                for(var student:course.getStudents()){
                    StudentDTO studentDTO=new StudentDTO();
                    studentDTO.setId(student.getId());
                    studentDTO.setName(student.getName());
                    studentDTOS.add(studentDTO);
                }
                courseDTO.setStudents(studentDTOS);
                courseDTOS.add(courseDTO);
            }
            InstituteDTO instituteDTO=new InstituteDTO();
            instituteDTO.setId(institute.getId());
            instituteDTO.setName(institute.getName());
            instituteDTO.setCourses(courseDTOS);
            return instituteDTO;
        }
        else{
            throw new RuntimeException("Institute Not Found");
        }

    }
    public AttendanceReponseModel getAttendance(AttendanceRequest request) throws ParseException {

        return fingerprintDeviceService.seletcAttendanceRequest(request);

    }

    public AttendanceReponseModel getAttendanceBySubjects(AttendanceRequest attendanceRequest) throws ParseException {
        return fingerprintDeviceService.getAllStudentsAttendanceForAllSubjects(attendanceRequest);
    }

    public Object verifyAttendance(VerifyList attendanceRequest) {
        return fingerprintDeviceService.verifyAttendance(attendanceRequest);

    }
}

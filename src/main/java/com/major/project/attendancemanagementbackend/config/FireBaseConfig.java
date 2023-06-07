package com.major.project.attendancemanagementbackend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.major.project.attendancemanagementbackend.constants.Gender;
import com.major.project.attendancemanagementbackend.constants.Role;
import com.major.project.attendancemanagementbackend.entity.*;
import com.major.project.attendancemanagementbackend.repository.*;
import com.major.project.attendancemanagementbackend.util.HolidayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Configuration
public class FireBaseConfig{
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    InstituteRepository instituteRepository;

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AttendanceRepository attendanceRepository;
    @Autowired
    CollegeStaffRepository staffRepository;
    @Bean
    FirebaseApp createFireBaseApp() throws IOException {
        Resource companyDataResource = new ClassPathResource("fluttertest-bcdba-firebase-adminsdk-7u1ao-82c4155c3a.json");

        FileInputStream serviceAccount = new FileInputStream(companyDataResource.getFile());

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("url")
                .build();


        return FirebaseApp.initializeApp(options);
    }
    @Bean
    void setDefaultAdmin(){
        Admin admin=new Admin();
        admin.setEmail("aniketchaudharyjavaprog@gmail.com");
        admin.setFirebaseId("MjRP3sphJghK5eaYfrzwn18vNYR2");
        admin.setMobile("7905671663");
        admin.setName("Aniket Chaudhary");
        admin.setAuthority(Role.ADMIN);
        admin.setGender(Gender.MALE);
        admin.setAddress("address");
        admin.setCity("city");
        admin.setPostalCode("273010");
        admin.setDob(OffsetDateTime.now());
        adminRepository.save(admin);
        Institute institute=new Institute();
        institute.setEmail("afdfd");
        institute.setFirebaseId("V10XBSCq1EfPLm1YbeTETe8FfQx1");
        institute.setMobile("afdfd");
        institute.setPostalCode("afdfd");
        institute.setAddress("afdfd");
        institute.setCollegeId(123l);
        institute.setName("afdfd");
        Institute save = instituteRepository.save(institute);
        Course course=new Course();
        course.setName("b.tech");
        course.setInstitute(save);
        Course save1 = courseRepository.save(course);
        Staff staff=new Staff();
        staff.setFirebaseId("ahcdlWkrclR96sguXCIpfz1nwaD2");
        staff.getCourses().add(save1);
        staff.setRole(Role.STAFF);
        staff.setAddress("dummy");
        Subject subject=new Subject();
        subject.setInstitute(save);
        subject.setCourse(save1);
        subject.setInstitute(save);
        subject.setName("dld");
        Subject save2 = subjectRepository.save(subject);
        Subject subject1=new Subject();
        subject1.setInstitute(save);
        subject1.setCourse(save1);
        subject1.setInstitute(save);
        subject1.setName("spdp");
        Subject save7 = subjectRepository.save(subject1);
        Student student=new Student();
        student.setName("aniket");
        student.setFirebaseId("QCWvFeQY0FNTmvJx7ftcOd0GmIY2");
        student.getSubjects().add(save2);
        student.getSubjects().add(save7);
        student.setCourse(save1);
        student.setInstitute(save);
        Student save3 = studentRepository.save(student);
        Student student1=new Student();
        student1.setName("shambhavi");
        student1.setCourse(save1);
        student1.setFirebaseId("B9PAQubYtwV8KKViPvniRL9PE2z1");
        student1.setInstitute(save);

        student1.getSubjects().add(save2);
        student1.getSubjects().add(save7);
        var list=new HashSet<Subject>();
        list.add(save2);
        list.add(save7);

        Student save4 = studentRepository.save(student1);

        staff.setSubjects(list);
        staff.setName("siidhant");
        staff.setInstitute(save);
        staffRepository.save(staff);

        for (int i = 0; i <= 5; i++) {
            LocalDate date = LocalDate.ofInstant(Instant.ofEpochMilli(1420050600000l), ZoneOffset.UTC).plusDays(i);
            Attendance attendance=new Attendance();
            attendance.setSubjectId(save2.getId());
            attendance.setCourseId(save1.getId());
            attendance.setInstituteId(save.getId());
            attendance.setStudentId(save3.getId());
            attendance.setDate(Date.valueOf(date));
            attendanceRepository.save(attendance);
        }
        for (int i = 0; i <= 3; i++) {
            LocalDate date = LocalDate.ofInstant(Instant.ofEpochMilli(1420050600000l), ZoneOffset.UTC).plusDays(i);
            Attendance attendance=new Attendance();
            attendance.setSubjectId(save2.getId());
            attendance.setCourseId(save1.getId());
            attendance.setInstituteId(save.getId());
            attendance.setStudentId(save4.getId());
            attendance.setDate(Date.valueOf(date));
            attendanceRepository.save(attendance);
        }
        for (int i = 0; i <= 5; i++) {
            LocalDate date = LocalDate.ofInstant(Instant.ofEpochMilli(1420050600000l), ZoneOffset.UTC).plusDays(i);
            Attendance attendance=new Attendance();
            attendance.setSubjectId(save7.getId());
            attendance.setCourseId(save1.getId());
            attendance.setInstituteId(save.getId());
            attendance.setStudentId(save3.getId());
            attendance.setDate(Date.valueOf(date));
            attendanceRepository.save(attendance);
        }
        for (int i = 0; i <= 3; i++) {
            LocalDate date = LocalDate.ofInstant(Instant.ofEpochMilli(1420050600000l), ZoneOffset.UTC).plusDays(i);
            Attendance attendance=new Attendance();
            attendance.setSubjectId(save7.getId());
            attendance.setCourseId(save1.getId());
            attendance.setInstituteId(save.getId());
            attendance.setStudentId(save4.getId());
            attendance.setDate(Date.valueOf(date));
            attendanceRepository.save(attendance);
        }


    }


}
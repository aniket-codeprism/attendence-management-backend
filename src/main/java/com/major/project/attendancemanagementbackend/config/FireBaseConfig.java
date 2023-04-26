package com.major.project.attendancemanagementbackend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.major.project.attendancemanagementbackend.constants.Gender;
import com.major.project.attendancemanagementbackend.constants.Role;
import com.major.project.attendancemanagementbackend.entity.Admin;
import com.major.project.attendancemanagementbackend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FireBaseConfig{
    @Autowired
    AdminRepository adminRepository;
    @Bean
    FirebaseApp createFireBaseApp() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/fluttertest-bcdba-firebase-adminsdk-7u1ao-82c4155c3a.json");

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
        adminRepository.save(admin);
    }
}
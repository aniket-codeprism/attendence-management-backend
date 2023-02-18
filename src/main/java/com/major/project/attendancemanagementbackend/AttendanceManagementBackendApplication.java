package com.major.project.attendancemanagementbackend;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.major.project.attendancemanagementbackend.repository")
@EntityScan("com.major.project.attendancemanagementbackend.entity")
public class AttendanceManagementBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceManagementBackendApplication.class, args);
	}

}

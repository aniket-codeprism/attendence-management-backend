package com.major.project.attendancemanagementbackend.repository;

import com.major.project.attendancemanagementbackend.entity.FingerprintDevice;
import com.major.project.attendancemanagementbackend.entity.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FingerRepository extends JpaRepository<FingerprintDevice, Long> {
//     List<FingerprintDevice> findAllByInstituteId(Long id);


}

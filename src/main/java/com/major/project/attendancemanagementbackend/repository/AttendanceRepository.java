package com.major.project.attendancemanagementbackend.repository;

import com.major.project.attendancemanagementbackend.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAllByStudentIdAndSubjectIdAndCourseIdAndInstituteIdAndDateGreaterThanEqualAndDateLessThanEqualAndVerified(Long studentId, Long subjectId, Long courseId, Long instituteId, Date start, Date end, Boolean verfied);

    List<Attendance> findAllByStudentIdAndDateGreaterThanEqualAndDateLessThanEqualAndVerified(long id, Date start, Date end,Boolean verfied);
    List<Attendance> findAllBySubjectIdAndAndCourseIdAndDateGreaterThanEqualAndDateLessThanEqualAndVerified(long subject,Long course, Date start, Date end,Boolean verified);
    List<Attendance> findAllByStudentIdAndSubjectIdAndDateGreaterThanEqualAndDateLessThanEqualAndVerified(long studentId,Long subjectId, Date start, Date end,Boolean verified);
    Optional<Attendance> findByStudentIdAndSubjectIdAndCourseIdAndInstituteIdAndDate(Long studentId, Long subjectId, Long courseId, Long instituteId, Date start);
}

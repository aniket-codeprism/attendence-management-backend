package com.major.project.attendancemanagementbackend.DTo;

import lombok.Data;

import java.util.ArrayList;
import java.util.*;

@Data
public class CourseDTO {
    Long id;
    String name;
    List<SubjectDTO> subjects=new ArrayList();
    List<StudentDTO> students=new ArrayList();

}
package com.major.project.attendancemanagementbackend.service;

import com.major.project.attendancemanagementbackend.DTo.AttendanceDTO;
import com.major.project.attendancemanagementbackend.entity.*;
import com.major.project.attendancemanagementbackend.models.*;
import com.major.project.attendancemanagementbackend.repository.*;
import com.major.project.attendancemanagementbackend.util.HolidayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class FingerprintDeviceService {
    @Autowired
    private FingerRepository fingerRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private CourseRepository courseRepository;

    public FingerprintDevice createFingerprintDevice(){
        return fingerRepository.save(new FingerprintDevice());
    }

    @MessageMapping("/user")
    public void getUser(String user) {
        System.out.println(user.toString());
    }
    @Autowired
    SimpMessagingTemplate template;

    public void sendMessageToDeviceId(Long id,String message) {

        template.convertAndSend("/topic/"+id, message);
    }

    public void registerFingerprint(Long id,Long studentId) {
        sendMessageToDeviceId(id,"register"+"."+studentId);
    }

//    public List<FingerprintDevice> getAllDevicesByInstitute() {
//        return fingerRepository.findAllByInstituteId(1l);
//    }

    public Optional<FingerprintDevice> findById(Long id) {
        return fingerRepository.findById(id);
    }

    public FingerprintDevice save(FingerprintDevice fingerprintDevice) {
        return fingerRepository.save(fingerprintDevice);
    }

    public DeviceVerifyResponse verify(Long id) {
        Optional<FingerprintDevice> byId = fingerRepository.findById(id);
        if(byId.isEmpty()) {
            DeviceVerifyResponse deviceVerifyResponse=new DeviceVerifyResponse();
            deviceVerifyResponse.setDeviceExists(-1);
            deviceVerifyResponse.setStatus(-1);
            return deviceVerifyResponse;
        }
        FingerprintDevice fingerprintDevice = byId.get();
        fingerprintDevice.getEnabled();
        DeviceVerifyResponse deviceVerifyResponse=new DeviceVerifyResponse();
        deviceVerifyResponse.setDeviceExists(1);
        deviceVerifyResponse.setStatus(fingerprintDevice.getEnabled()?1:-1);
        return deviceVerifyResponse;
    }

    public List<FingerprintDevice> getAllDevices() {
        List<FingerprintDevice> all = fingerRepository.findAll();
        return all;
    }
    public List<Attendance> registerAttendance(UserModel userModel) throws IOException {
        Optional<Student> studentOptional = studentRepository.findById(userModel.getId());
        if(studentOptional.isEmpty()){
            throw new RuntimeException("Student,Institute,Subject or Course Not Found");
        }
        CalenderResponse event = HolidayUtil.checkHoliday(new Date(Instant.now().toEpochMilli()).toString());
        if(event.getItems().size()>0){
            throw new RuntimeException("Its a Holiday");
        }
        Date date = new Date(Instant.now().toEpochMilli());
        List<Attendance> attendances=new ArrayList<>();
        for(var subject:studentOptional.get().getSubjects()){
            Attendance attendance1=new Attendance();
            attendance1.setDate(date);
            attendance1.setCourseId(studentOptional.get().getCourse().getId());
            attendance1.setStudentId(studentOptional.get().getId());
            attendance1.setInstituteId(studentOptional.get().getInstitute().getId());
            attendance1.setSubjectId(subject.getId());
            if(attendanceRepository.findByStudentIdAndSubjectIdAndCourseIdAndInstituteIdAndDate(studentOptional.get().getId(),subject.getId(),
                    studentOptional.get().getCourse().getId(),studentOptional.get().getInstitute().getId(),date

            ).isPresent()){
                throw new RuntimeException("Attendace already Made");
            }

            attendances.add(attendanceRepository.save(attendance1));
        }
        System.out.println(attendances.size());
        return attendances;

    }
    public AttendanceReponseModel getAttendanceBySubject(AttendanceRequest attendanceRequest) throws ParseException {
        Optional<Subject> byId = subjectRepository.findById(attendanceRequest.getSubjectId());
        Date date1 = new Date(getSqlDate(attendanceRequest.getStartDate()));
        Date date = new Date(getSqlDate(attendanceRequest.getEndDate()));
        List<Attendance> allByStudentIdAndSubjectIdAndCourseIdAndInstituteId = attendanceRepository.findAllByStudentIdAndSubjectIdAndCourseIdAndInstituteIdAndDateGreaterThanEqualAndDateLessThanEqualAndVerified(attendanceRequest.getStudentId(),
                attendanceRequest.getSubjectId(), attendanceRequest.getCourseId(), attendanceRequest.getInstituteId(),
                date1,date,attendanceRequest.getVerfied()
        );
        List<AttendanceDTO> attendanceDTOS=new ArrayList<>();
        for(var attendance:allByStudentIdAndSubjectIdAndCourseIdAndInstituteId){
            AttendanceDTO attendanceDTO=new AttendanceDTO();
            attendanceDTO.setValue(1);
            attendanceDTO.setDate(attendance.getDate().toLocalDate().toString());
            attendanceDTO.setId(attendance.getId());
            attendanceDTOS.add(attendanceDTO);
        }
        List<String> datesBetween = getDatesBetween(date1.toLocalDate(), date.toLocalDate());
        HashMap<String,List<AttendanceDTO>> map=new HashMap<>();
        map.put(byId.get().getName(),attendanceDTOS);
        AttendanceReponseModel attendanceReponseModel=new AttendanceReponseModel();
        attendanceReponseModel.getAttendaces().add(map);
        attendanceReponseModel.getDates().addAll(datesBetween);
        return attendanceReponseModel;
    }
    public long getSqlDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            java.util.Date utilDate = dateFormat.parse(date);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.toInstant().toEpochMilli());

            System.out.println("Parsed Date: " + utilDate.toInstant().toEpochMilli());
            return utilDate.toInstant().toEpochMilli();

    }
    public static List<String> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        List<String> dates = new ArrayList<>();
        long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);

        for (int i = 0; i <= numOfDays; i++) {
            LocalDate date = startDate.plusDays(i);
            dates.add(date.toString());
        }

        return dates;
    }

    public AttendanceReponseModel getAttendanceByInstitute(AttendanceRequest request) throws ParseException {
        Optional<Institute> byId = instituteRepository.findById(request.getInstituteId());
        if(byId.isEmpty()){
            throw  new RuntimeException("Institute not Found");
        }
        Institute institute = byId.get();
        Set<Student> students = institute.getStudents();
        Date date1 = new Date(getSqlDate(request.getStartDate()));
        Date date = new Date(getSqlDate(request.getEndDate()));
        List<String> datesBetween = getDatesBetween(date1.toLocalDate(), date.toLocalDate());
        AttendanceReponseModel attendanceReponseModel=new AttendanceReponseModel();
        List<HashMap<String,List<AttendanceDTO>>> attendaces=new ArrayList<>();
        List<String> columns=new ArrayList<>();
        columns.add("Total");
        attendanceReponseModel.setDates(columns);
        for(var student:students){
            HashMap<String,List<AttendanceDTO>> data=new HashMap<>();
            List<Attendance> attendances = attendanceRepository.findAllByStudentIdAndDateGreaterThanEqualAndDateLessThanEqualAndVerified(student.getId(),date1,date,request.getVerfied());
            int i = (attendances.size()*100) / datesBetween.size();
            AttendanceDTO dto=new AttendanceDTO();
            dto.setDate("Total");
            dto.setValue(i);
            List<AttendanceDTO> dtos=new ArrayList<>();
            dtos.add(dto);
            data.put(student.getName(),dtos);
            attendaces.add(data);
        }
        attendanceReponseModel.setAttendaces(attendaces);
        return attendanceReponseModel;
    }
    public AttendanceReponseModel getAttendanceAllSubjects( AttendanceRequest request) throws ParseException {
        Optional<Student> byId = studentRepository.findById(request.getStudentId());
        if(byId.isEmpty()){
            throw new RuntimeException("Student not Found");
        }
        Student student = byId.get();
        Set<Subject> subjects = student.getSubjects();
        Date date1 = new Date(getSqlDate(request.getStartDate()));
        Date date = new Date(getSqlDate(request.getEndDate()));
        List<String> datesBetween = getDatesBetween(date1.toLocalDate(), date.toLocalDate());
        AttendanceReponseModel attendanceReponseModel=new AttendanceReponseModel();
        List<HashMap<String,List<AttendanceDTO>>> attendaces=new ArrayList<>();
        List<String> columns=new ArrayList<>();
        columns.add("Total");
        attendanceReponseModel.setDates(columns);
        for(var subject:subjects){
            HashMap<String,List<AttendanceDTO>> data=new HashMap<>();
            List<AttendanceDTO> attendanceDTOS=new ArrayList<>();
            List<Attendance> allByStudentIdAndSubjectId = attendanceRepository.findAllByStudentIdAndSubjectIdAndDateGreaterThanEqualAndDateLessThanEqualAndVerified(student.getId(), subject.getId(),date1,date,request.getVerfied());
            System.out.println(allByStudentIdAndSubjectId.size());
            AttendanceDTO attendanceDTO=new AttendanceDTO();
            attendanceDTO.setValue((allByStudentIdAndSubjectId.size()*100)/datesBetween.size());
            attendanceDTO.setDate("Total");
            attendanceDTOS.add(attendanceDTO);
            data.put(subject.getName(),attendanceDTOS);
            attendaces.add(data);
        }
        attendanceReponseModel.setAttendaces(attendaces);
        return attendanceReponseModel;
    }
    public AttendanceReponseModel getAllStudentsAttendanceForAllSubjects( AttendanceRequest request) throws ParseException {
        Optional<Course> byId = courseRepository.findById(request.getCourseId());
        if(byId.isEmpty()){
            throw new RuntimeException("Student not Found");
        }
        Course course = byId.get();
        Set<Subject> subjects = course.getSubjects();
        Date date1 = new Date(getSqlDate(request.getStartDate()));
        Date date = new Date(getSqlDate(request.getEndDate()));
        List<String> datesBetween = getDatesBetween(date1.toLocalDate(), date.toLocalDate());
        AttendanceReponseModel attendanceReponseModel=new AttendanceReponseModel();
        List<HashMap<String,List<AttendanceDTO>>> attendaces=new ArrayList<>();
        Set<String> columns=new HashSet<>();
        for(var student:course.getStudents()) {
        HashMap<String,List<AttendanceDTO>> map=new HashMap<>();
            for(var subject:subjects){
                columns.add(subject.getName());
                List<Attendance> allBySubjectIdAndAndCourseId = attendanceRepository.findAllByStudentIdAndSubjectIdAndDateGreaterThanEqualAndDateLessThanEqualAndVerified(student.getId(), subject.getId(),date1,date,request.getVerfied());
                AttendanceDTO attendanceDTO=new AttendanceDTO();
                attendanceDTO.setValue((allBySubjectIdAndAndCourseId.size()*100)/datesBetween.size());
                attendanceDTO.setDate(subject.getName());
                List<AttendanceDTO> attendanceDTOS=new ArrayList<>();
                attendanceDTOS.add(attendanceDTO);
                if(map.containsKey(student.getName())){
                    List<AttendanceDTO> attendanceDTOS1 = map.get(student.getName());
                    attendanceDTOS1.addAll(attendanceDTOS);
                    map.put(student.getName(),attendanceDTOS1);
                }
                else{
                    map.put(student.getName(),attendanceDTOS);
                }
            }
            attendaces.add(map);
        }
        attendanceReponseModel.setDates(columns.stream().toList());
        attendanceReponseModel.setAttendaces(attendaces);
        return attendanceReponseModel;
    }
    public AttendanceReponseModel seletcAttendanceRequest(AttendanceRequest request) throws ParseException {
        if(request.getInstituteId()!=0&&request.getCourseId()==0&&request.getSubjectId()==0&&request.getStudentId()==0){
            return getAttendanceByInstitute(request);
        }
        if(request.getInstituteId()!=0&&request.getCourseId()!=0&&request.getSubjectId()==0&&request.getStudentId()==0){
            return getAllStudentsAttendanceForAllSubjects(request);
        }
        if(request.getInstituteId()!=0&&request.getCourseId()!=0&&request.getSubjectId()!=0&&request.getStudentId()!=0){
            return getAttendanceBySubject(request);
        }
        if(request.getInstituteId()!=0&&request.getCourseId()!=0&&request.getSubjectId()==0&&request.getStudentId()!=0){
            return getAttendanceAllSubjects(request);
        }
        if(request.getInstituteId()!=0&&request.getCourseId()!=0&&request.getSubjectId()!=0&&request.getStudentId()==0){
            return getAttendanceAllStudentsForSubject(request);
        }
        return  new AttendanceReponseModel();
    }

    private AttendanceReponseModel getAttendanceAllStudentsForSubject(AttendanceRequest request) throws ParseException {
        Optional<Subject> byId = subjectRepository.findById(request.getSubjectId());
        if(byId.isEmpty()){
            throw new RuntimeException("Subject not Found");
        }
        Subject subject = byId.get();
        Set<Student> students = subject.getStudents();
        Date date1 = new Date(getSqlDate(request.getStartDate()));
        Date date = new Date(getSqlDate(request.getEndDate()));
        List<String> datesBetween = getDatesBetween(date1.toLocalDate(), date.toLocalDate());
        datesBetween.add(0,"Total");
        List<HashMap<String,List<AttendanceDTO>>> attendaces=new ArrayList<>();
        System.out.println("students"+students.size());
        for(var student:students){
            HashMap<String,List<AttendanceDTO>> map=new HashMap<>();
            List<AttendanceDTO>attendanceDTOS=new ArrayList<>();
            List<Attendance> allByStudentIdAndSubjectIdAndDateGreaterThanEqualAndDateLessThanEqual = attendanceRepository.findAllByStudentIdAndSubjectIdAndDateGreaterThanEqualAndDateLessThanEqualAndVerified(student.getId(), subject.getId(), date1, date,request.getVerfied());
            for(var attendance:allByStudentIdAndSubjectIdAndDateGreaterThanEqualAndDateLessThanEqual){
                AttendanceDTO attendanceDTO=new AttendanceDTO();
                attendanceDTO.setId(attendance.getId());
                attendanceDTO.setDate(attendance.getDate().toLocalDate().toString());
                attendanceDTO.setValue(1);
                attendanceDTOS.add(attendanceDTO);
            }
            AttendanceDTO attendanceDTO=new AttendanceDTO();
            attendanceDTO.setDate("Total");
            attendanceDTO.setValue((allByStudentIdAndSubjectIdAndDateGreaterThanEqualAndDateLessThanEqual.size()));
            attendanceDTOS.add(0,attendanceDTO);
            map.put(student.getId()+"-"+student.getName(),attendanceDTOS);
            attendaces.add(map);

        }

        AttendanceReponseModel attendanceReponseModel=new AttendanceReponseModel();
        attendanceReponseModel.setDates(datesBetween);
        attendanceReponseModel.setAttendaces(attendaces);
        return attendanceReponseModel;
    }

    public Object verifyAttendance(VerifyList attendanceRequest) {
        for(var item:attendanceRequest.getVerifyList()){
            if(item!=null){
                Optional<Attendance> byId = attendanceRepository.findById(item);
                if(byId.isPresent()){
                    System.out.println(byId.get().toString());
                    byId.get().setVerified(true);
                    Attendance save = attendanceRepository.save(byId.get());
                    System.out.println(save.toString());

                }
            }
        }
        return "Done";
    }
}

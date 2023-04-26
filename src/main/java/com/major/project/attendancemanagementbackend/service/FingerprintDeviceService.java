package com.major.project.attendancemanagementbackend.service;

import com.major.project.attendancemanagementbackend.entity.FingerprintDevice;
import com.major.project.attendancemanagementbackend.models.DeviceVerifyResponse;
import com.major.project.attendancemanagementbackend.repository.FingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FingerprintDeviceService {
    @Autowired
    private FingerRepository fingerRepository;
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
}

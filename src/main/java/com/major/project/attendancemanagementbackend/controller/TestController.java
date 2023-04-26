package com.major.project.attendancemanagementbackend.controller;

import com.major.project.attendancemanagementbackend.entity.FingerprintDevice;
import com.major.project.attendancemanagementbackend.repository.FingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    String global = "started";
    int val = 5;
    @Autowired
    FingerRepository fingerRepository;

    @GetMapping(path = "/test")
    public ResponseEntity registerInstitute() {
        return ResponseEntity.ok().build();
    }

//    @RequestMapping(value = "/streams", method = RequestMethod.GET)
//    public StreamingResponseBody getStreamingResponse () {
//        Thread t=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0;i<20;i++){
//                    global=global+val;
//                    val=val+val;
//                    try {
//                        Thread.sleep(5);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        });
//        return new StreamingResponseBody() {
//            @Override
//            public void writeTo (OutputStream out) throws IOException {
//                t.start();
//                for (int i = 0; i < 1000; i++) {
//                    out.write((global+"\n").getBytes());
//
//                    out.flush();
//                    try {
//                        Thread.sleep(5);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//    }
}

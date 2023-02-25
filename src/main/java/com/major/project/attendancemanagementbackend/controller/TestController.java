package com.major.project.attendancemanagementbackend.controller;

import com.major.project.attendancemanagementbackend.entity.Institute;
import com.major.project.attendancemanagementbackend.models.InstituteModel;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
public class TestController {
    String global="started";
    int val=5;
    @GetMapping(path = "/test")
    public ResponseEntity<String> registerInstitute()
    {

        return ResponseEntity.ok("Hello World");
    }

    @RequestMapping(value = "/streams", method = RequestMethod.GET)
    public StreamingResponseBody getStreamingResponse () {
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<20;i++){
                    global=global+val;
                    val=val+val;
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        return new StreamingResponseBody() {
            @Override
            public void writeTo (OutputStream out) throws IOException {
                t.start();
                for (int i = 0; i < 1000; i++) {
                    out.write((global+"\n").getBytes());

                    out.flush();
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}

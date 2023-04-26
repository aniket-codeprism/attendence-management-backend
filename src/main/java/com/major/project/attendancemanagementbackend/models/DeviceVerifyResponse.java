package com.major.project.attendancemanagementbackend.models;

import lombok.Builder;
import lombok.Data;

@Data
public class DeviceVerifyResponse {
    int deviceExists;
    int status;
}

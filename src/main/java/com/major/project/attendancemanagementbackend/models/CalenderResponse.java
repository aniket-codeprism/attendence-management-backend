package com.major.project.attendancemanagementbackend.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class CalenderResponse {
    List<Event> items=new ArrayList<>();
    @Data
    public class Event{
        String summary;
        String description;
    }
}

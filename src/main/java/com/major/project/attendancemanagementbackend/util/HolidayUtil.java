package com.major.project.attendancemanagementbackend.util;


import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.major.project.attendancemanagementbackend.models.CalenderResponse;

import java.io.IOException;

public class HolidayUtil {
    public static CalenderResponse checkHoliday(String startDate) throws IOException {
        String baseurl="https://www.googleapis.com/calendar/v3/calendars/%s/events?key=%s&timeMin=%sT00:00:00Z&timeMax=%sT23:59:59Z&maxResults=100";
        String calenderId="en.indian%23holiday%40group.v.calendar.google.com";
        String apiKey="AIzaSyCB_TBBmHRgRf_7NGCgELy86gr9q7f4LWA";
        String endDate=startDate;
        HttpTransport transport = new NetHttpTransport();
        HttpRequestFactory requestFactory = transport.createRequestFactory();
        GenericUrl url = new GenericUrl(String.format(baseurl,calenderId,apiKey,startDate,endDate));
        HttpRequest request = requestFactory.buildGetRequest(url);
        com.google.api.client.http.HttpResponse response = request.execute();
        CalenderResponse event = new Gson().fromJson(new String(response.getContent().readAllBytes()), CalenderResponse.class);
        return event;

    }
}

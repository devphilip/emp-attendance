package com.devphilip.empattendance.service;

import com.devphilip.empattendance.dto.CheckInOutDto;
import com.devphilip.empattendance.model.Attendance;
import org.springframework.data.domain.Page;

public interface AttendanceService {

    String checkIn(CheckInOutDto attendance);

    String checkOut(CheckInOutDto attendance);

    String reportSick(CheckInOutDto attendance);

    Page<Attendance> getAttendances(int page, int pageSize, String staffId, String status, String startDate, String endDate);

}

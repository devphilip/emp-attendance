package com.devphilip.empattendance.service;

import com.devphilip.empattendance.constant.AttendanceStatus;
import com.devphilip.empattendance.dto.CheckInOutDto;
import com.devphilip.empattendance.exception.BadRequestException;
import com.devphilip.empattendance.model.Attendance;
import com.devphilip.empattendance.model.Employee;
import com.devphilip.empattendance.repository.AttendanceRepository;
import com.devphilip.empattendance.repository.specification.AttendanceSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeService employeeService;

    @Override
    public String checkIn(CheckInOutDto dto) {
        Employee employee = employeeService
                .findByStaffIdAndPassword(dto.getStaffId(), dto.getPassword())
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        LocalDate localDate = LocalDate.now();
        Attendance attendance = Attendance.builder()
                .employee(employee)
                .timeIn(LocalTime.now())
                .date(localDate)
                .aMonth(localDate.getMonth().name().toLowerCase())
                .status(AttendanceStatus.PRESENT.name())
                .build();
        attendanceRepository.save(attendance);
        return employee.getFirstName() + " " + employee.getLastName() + " signed in successfully";
    }

    @Override
    public String checkOut(CheckInOutDto dto) {
        Employee employee = employeeService.findByStaffIdAndPassword(dto.getStaffId(), dto.getPassword())
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        Attendance attendance = attendanceRepository.findByEmployeeIdAndStatus(employee.getId(), AttendanceStatus.PRESENT.name())
                .orElseThrow(() -> new BadRequestException("You have not Signed in yet"));

        attendance.setTimeOut(LocalTime.now());
        attendanceRepository.save(attendance);
        return employee.getFirstName() + " " + employee.getLastName() + " signed out successfully";
    }

    @Override
    public String reportSick(CheckInOutDto dto) {
        Employee employee = employeeService
                .findByStaffIdAndPassword(dto.getStaffId(), dto.getPassword())
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));

        LocalDate localDate = LocalDate.now();
        Attendance attendance = Attendance.builder()
                .employee(employee)
                .timeIn(LocalTime.now())
                .date(localDate)
                .aMonth(localDate.getMonth().name().toUpperCase())
                .status(AttendanceStatus.SICK.name())
                .build();
        attendanceRepository.save(attendance);
        return employee.getFirstName() + " " + employee.getLastName() + " reported sick today";
    }


    @Override
    public Page<Attendance> getAttendances(int page, int pageSize, String staffId, String status, String startDate, String endDate) {

        Specification<Attendance> specification = buildSpecification(staffId, status, startDate, endDate);
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
//        Pageable pageable = PageRequest.of(page-1, pageSize, sort);
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        return attendanceRepository.findAll(specification, pageable);
    }

    private Specification<Attendance> buildSpecification(String staffId, String status, String startDate, String endDate) {

        Specification<Attendance> attendanceSpecification = Specification.where(null);

        if (StringUtils.hasText(staffId)) {
            attendanceSpecification = attendanceSpecification.and(AttendanceSpecification.byStaffId(staffId));
        }

        if (StringUtils.hasText(startDate) && StringUtils.hasText(endDate)) {
            attendanceSpecification = attendanceSpecification.and(AttendanceSpecification.byDateRange(startDate, endDate));
        } else if (StringUtils.hasText(startDate)) {
            attendanceSpecification = attendanceSpecification.and(AttendanceSpecification.byDate(startDate));
        } else if (StringUtils.hasText(endDate)) {
            attendanceSpecification = attendanceSpecification.and(AttendanceSpecification.byDate(endDate));
        }

        if (StringUtils.hasText(status)) {
            attendanceSpecification = attendanceSpecification.and(AttendanceSpecification.byStatus(status));
        } else {
            attendanceSpecification = attendanceSpecification.and(AttendanceSpecification.byStatus(AttendanceStatus.PRESENT.name()));
        }

        return attendanceSpecification;
    }
}

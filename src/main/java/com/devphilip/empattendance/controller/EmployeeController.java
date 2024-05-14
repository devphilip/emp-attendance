package com.devphilip.empattendance.controller;

import com.devphilip.empattendance.dto.CheckInOutDto;
import com.devphilip.empattendance.dto.EmployeeDto;
import com.devphilip.empattendance.model.Employee;
import com.devphilip.empattendance.service.AttendanceService;
import com.devphilip.empattendance.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final AttendanceService attendanceService;


    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody @Valid EmployeeDto employeeDto, UriComponentsBuilder urlBuilder) {
        Employee employee = employeeService.addEmployee(employeeDto);
        UriComponents uriComponents = urlBuilder.path("/employees/{id}").buildAndExpand(employee.getId());
        return ResponseEntity.created(uriComponents.toUri()).body("employee added successfully");
    }

    @GetMapping
    public ResponseEntity<?> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @PostMapping("/attendance/check-in")
    public ResponseEntity<?> checkIn(@RequestBody CheckInOutDto checkInDto) {
        return ResponseEntity.ok(attendanceService.checkIn(checkInDto));
    }

    @PostMapping("/attendance/check-out")
    public ResponseEntity<?> checkOut(@RequestBody CheckInOutDto employeeDto) {
        return ResponseEntity.ok(attendanceService.checkOut(employeeDto));
    }

    @PostMapping("/attendance/report-sick")
    public ResponseEntity<?> reportSick(@RequestBody CheckInOutDto employeeDto) {
        return ResponseEntity.ok(attendanceService.reportSick(employeeDto));
    }

    @GetMapping("/attendance")
    public ResponseEntity<?> getOrders(@RequestParam(defaultValue = "0", required = false) int page,
                                       @RequestParam(defaultValue = "20", required = false) int pageSize,
                                       @RequestParam(defaultValue = "", required = false) String staffId,
                                       @RequestParam(defaultValue = "", required = false) String status,
                                       @RequestParam(defaultValue = "", required = false) String startDate,
                                       @RequestParam(defaultValue = "", required = false) String endDate) {

        return ResponseEntity.ok(attendanceService.getAttendances(page, pageSize, staffId, status, startDate, endDate));
    }

}

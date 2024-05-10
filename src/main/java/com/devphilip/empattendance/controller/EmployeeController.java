package com.devphilip.empattendance.controller;

import com.devphilip.empattendance.dto.CheckInOutDto;
import com.devphilip.empattendance.dto.DepartmentDto;
import com.devphilip.empattendance.dto.EmployeeDto;
import com.devphilip.empattendance.model.Department;
import com.devphilip.empattendance.model.Employee;
import com.devphilip.empattendance.service.EmployeeService;
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


    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto employeeDto, UriComponentsBuilder urlBuilder) {
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

    @PostMapping("/checkin-out")
    public ResponseEntity<?> checkInOut(@RequestBody CheckInOutDto employeeDto) {
        return ResponseEntity.ok("Implement check-in and check-out");
    }

}

package com.devphilip.empattendance.service;

import com.devphilip.empattendance.dto.EmployeeDto;
import com.devphilip.empattendance.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee addEmployee(EmployeeDto employeeDto);

    List<Employee> getEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, EmployeeDto employeeDto);

    void deleteEmployee(Long id);

}

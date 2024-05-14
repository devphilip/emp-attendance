package com.devphilip.empattendance.service;

import com.devphilip.empattendance.dto.EmployeeDto;
import com.devphilip.empattendance.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee addEmployee(EmployeeDto employeeDto);

    List<Employee> getEmployees();

    Page<Employee> getEmployees(int page, int pageSize, Long departmentId, String empClass);

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, EmployeeDto employeeDto);

    void deleteEmployee(Long id);

    Optional<Employee> findByStaffIdAndPassword(String staffId, String password);

    Optional<Employee> findByStaffId(String staffId);
}

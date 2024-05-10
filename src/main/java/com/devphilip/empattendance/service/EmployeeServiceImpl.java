package com.devphilip.empattendance.service;

import com.devphilip.empattendance.dto.EmployeeDto;
import com.devphilip.empattendance.exception.NotFoundException;
import com.devphilip.empattendance.model.Department;
import com.devphilip.empattendance.model.Employee;
import com.devphilip.empattendance.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;

    @Override
    public Employee addEmployee(EmployeeDto dto) {

        Department department = departmentService.getDepartmentById(dto.getDepartmentId());

        Employee employee = Employee.builder()
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .staffId(dto.getStaffId())
                .password(dto.getPassword())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .gender(dto.getGender())
                .build();
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found"));
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id).ifPresent(employeeRepository::delete);
    }
}

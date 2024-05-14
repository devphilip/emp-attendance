package com.devphilip.empattendance.service;

import com.devphilip.empattendance.dto.EmployeeDto;
import com.devphilip.empattendance.exception.NotFoundException;
import com.devphilip.empattendance.model.Department;
import com.devphilip.empattendance.model.Employee;
import com.devphilip.empattendance.repository.EmployeeRepository;
import com.devphilip.empattendance.repository.specification.EmployeeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
                .gender(dto.getGender().name())
                .employeeClass(dto.getEmployeeClass().name())
                .build();
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Page<Employee> getEmployees(int page, int pageSize, Long departmentId, String empClass) {

        Specification<Employee> spec = Specification.where(null);
        if (departmentId != null) {
            spec = spec.and(EmployeeSpecification.byDepartmentId(departmentId));
        }

        if (StringUtils.hasText(empClass)) {
            spec = spec.and(EmployeeSpecification.byEmployeeClass(empClass));
        }

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        return employeeRepository.findAll(spec, pageable);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDto dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found"));

        if (Objects.isNull(dto)) {
            return employee;
        }

        if (StringUtils.hasText(dto.getFirstName())) employee.setFirstName(dto.getLastName());
        if (StringUtils.hasText(dto.getLastName())) employee.setLastName(dto.getLastName());
        if (StringUtils.hasText(dto.getEmail())) employee.setEmail(dto.getEmail());
        if (StringUtils.hasText(dto.getStaffId())) employee.setStaffId(dto.getStaffId());
        if (StringUtils.hasText(dto.getPassword())) employee.setPassword(dto.getPassword());
        if (StringUtils.hasText(dto.getAddress())) employee.setAddress(dto.getAddress());
        if (StringUtils.hasText(dto.getPhone())) employee.setPhone(dto.getPhone());
        if (StringUtils.hasText(dto.getGender().name())) employee.setGender(dto.getGender().name());
        if (StringUtils.hasText(dto.getEmployeeClass().name()))
            employee.setEmployeeClass(dto.getEmployeeClass().name());

        if (Objects.nonNull(dto.getDepartmentId())) {
            Department department = departmentService.getDepartmentById(dto.getDepartmentId());
            employee.setDepartment(department);
        }

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id).ifPresent(employeeRepository::delete);
    }

    @Override
    public Optional<Employee> findByStaffIdAndPassword(String staffId, String password) {
        return employeeRepository.findByStaffIdAndPassword(staffId, password);
    }

    @Override
    public Optional<Employee> findByStaffId(String staffId) {
        return employeeRepository.findByStaffId(staffId);
    }
}

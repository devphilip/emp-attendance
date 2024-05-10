package com.devphilip.empattendance.service;

import com.devphilip.empattendance.dto.DepartmentDto;
import com.devphilip.empattendance.exception.NotFoundException;
import com.devphilip.empattendance.model.Department;
import com.devphilip.empattendance.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department addDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Department not found"));
    }

    @Override
    public Department updateDepartment(Long id, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            department = new Department();
        }
        department.setName(departmentDto.getName());
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}

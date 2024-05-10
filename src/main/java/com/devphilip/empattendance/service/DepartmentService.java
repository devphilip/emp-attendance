package com.devphilip.empattendance.service;

import com.devphilip.empattendance.dto.DepartmentDto;
import com.devphilip.empattendance.model.Department;

import java.util.List;

public interface DepartmentService {

    Department addDepartment(DepartmentDto departmentDto);

    List<Department> getDepartments();

    Department getDepartmentById(Long id);

    Department updateDepartment(Long id, DepartmentDto departmentDto);

    void deleteDepartment(Long id);

}

package com.devphilip.empattendance.repository;

import com.devphilip.empattendance.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
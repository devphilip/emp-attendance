package com.devphilip.empattendance.repository;

import com.devphilip.empattendance.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
package com.devphilip.empattendance.repository;

import com.devphilip.empattendance.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    Optional<Employee> findByStaffIdAndPassword(String staffId, String password);

    Optional<Employee> findByStaffId(String staffId);
}
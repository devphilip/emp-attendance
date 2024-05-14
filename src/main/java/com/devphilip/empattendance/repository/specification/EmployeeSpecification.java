package com.devphilip.empattendance.repository.specification;

import com.devphilip.empattendance.model.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

    public static Specification<Employee> byDepartmentId(Long departmentId) {
        return (root, query, cb) -> cb.equal(root.get("department").get("id"), departmentId);
    }

    public static Specification<Employee> byEmployeeClass(String empClass) {
        return (root, query, cb) -> cb.equal(root.get("employeeClass"), empClass);
    }

}

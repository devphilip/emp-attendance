package com.devphilip.empattendance;

import com.devphilip.empattendance.model.Department;
import com.devphilip.empattendance.model.Employee;
import com.devphilip.empattendance.repository.DepartmentRepository;
import com.devphilip.empattendance.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmpAttendanceApplication implements CommandLineRunner {

	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmpAttendanceApplication.class, args);
	}

	// seed data for testing
	@Override
	public void run(String... args) throws Exception {

		Department hr = departmentRepository.save(new Department("HR"));
		Department it = departmentRepository.save(new Department("IT"));
		Department sales = departmentRepository.save(new Department("Sales"));
		Department marketing = departmentRepository.save(new Department("Marketing"));

		employeeRepository.save(new Employee("EMP01", "EMP01",  "John", "Doe", "a@a.com", "123456", "123 Main St", "Male", hr));
		employeeRepository.save(new Employee("EMP02", "EMP02",  "Jane", "Doe", "b@b.com", "123456", "123 Main St", "Female", it));
		employeeRepository.save(new Employee("EMP03", "EMP03",  "Jack", "Doe", "c@c.com", "123456", "123 Main St", "Male", sales));
		employeeRepository.save(new Employee("EMP04", "EMP04",  "Jill", "Doe", "d@d.com", "123456", "123 Main St", "Female", marketing));
		employeeRepository.save(new Employee("EMP05", "EMP05",  "James", "Bower", "e@e.com", "123456", "123 Main St", "Male", hr));
		employeeRepository.save(new Employee("EMP06", "EMP06",  "Jenny", "Bower", "f@f.com", "123456", "123 Main St", "Female", it));
		employeeRepository.save(new Employee("EMP07", "EMP07",  "Jim", "Bower", "g@g.com", "123456", "123 Main St", "Male", sales));
		employeeRepository.save(new Employee("EMP08", "EMP08",  "Jenny", "Bower", "h@h.com", "123456", "123 Main St", "Female", marketing));

	}

}

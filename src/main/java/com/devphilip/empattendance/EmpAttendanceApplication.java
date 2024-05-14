package com.devphilip.empattendance;

import com.devphilip.empattendance.constant.EmployeeClass;
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

		Department hr = departmentRepository.save(Department.builder().name("HR").build());
		Department it = departmentRepository.save(Department.builder().name("IT").build());
		Department sales = departmentRepository.save(Department.builder().name("Sales").build());
		Department marketing = departmentRepository.save(Department.builder().name("Marketing").build());
		Department labs = departmentRepository.save(Department.builder().name("Labs").build());
		Department theatre = departmentRepository.save(Department.builder().name("Theatre").build());

		employeeRepository.save(Employee.builder().staffId("EMP01").password("EMP01").firstName("John").lastName("Doe").email("a@a.com").phone("123456").address("123 Main St").gender("Male").department(hr).employeeClass(EmployeeClass.NON_MEDICAL.name()).build());
		employeeRepository.save(Employee.builder().staffId("EMP02").password("EMP02").firstName("Jane").lastName("Doe").email("b@b.com").phone("123456").address("123 Main St").gender("Female").department(it).employeeClass(EmployeeClass.NON_MEDICAL.name()).build());
		employeeRepository.save(Employee.builder().staffId("EMP03").password("EMP03").firstName("Jack").lastName("Doe").email("c@c.com").phone("123456").address("123 Main St").gender("Male").department(sales).employeeClass(EmployeeClass.NON_MEDICAL.name()).build());
		employeeRepository.save(Employee.builder().staffId("EMP04").password("EMP04").firstName("Jill").lastName("Doe").email("d@d.com").phone("123456").address("123 Main St").gender("Female").department(marketing).employeeClass(EmployeeClass.NON_MEDICAL.name()).build());
		employeeRepository.save(Employee.builder().staffId("EMP05").password("EMP05").firstName("James").lastName("Bower").email("e@e.com").phone("123456").address("123 Main St").gender("Male").department(hr).employeeClass(EmployeeClass.NON_MEDICAL.name()).build());
		employeeRepository.save(Employee.builder().staffId("EMP06").password("EMP06").firstName("Jill").lastName("Doe").email("f@f.com").phone("123456").address("123 Main St").gender("Female").department(it).employeeClass(EmployeeClass.NON_MEDICAL.name()).build());
		employeeRepository.save(Employee.builder().staffId("EMP07").password("EMP07").firstName("Jack").lastName("Doe").email("g@g.com").phone("123456").address("123 Main St").gender("Male").department(sales).employeeClass(EmployeeClass.NON_MEDICAL.name()).build());
		employeeRepository.save(Employee.builder().staffId("EMP08").password("EMP08").firstName("Jane").lastName("Doe").email("h@h.com").phone("123456").address("123 Main St").gender("Female").department(marketing).employeeClass(EmployeeClass.NON_MEDICAL.name()).build());
	}

}

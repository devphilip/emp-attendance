package com.devphilip.empattendance.dto;

import com.devphilip.empattendance.constant.EmployeeClass;
import com.devphilip.empattendance.constant.Gender;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeUpdateDto implements Serializable {
    private String staffId;
    private String password;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phone;
    private String address;
    private Gender gender; // male or female
    private Long departmentId;
    private EmployeeClass employeeClass;

}
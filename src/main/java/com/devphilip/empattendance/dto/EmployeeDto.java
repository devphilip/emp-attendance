package com.devphilip.empattendance.dto;

import com.devphilip.empattendance.constant.EmployeeClass;
import com.devphilip.empattendance.constant.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.devphilip.empattendance.model.Employee}
 */
@Data
public class EmployeeDto implements Serializable {
    @NotNull(message = "Please Provide Staff Id")
    @NotEmpty(message = "Please Provide Staff Id")
    private String staffId;
    @NotNull(message = "Please Enter Staff chock-in pasword")
    @NotEmpty(message = "Please Enter Staff chock-in pasword")
    private String password;
    @NotNull(message = "First name is required")
    @NotEmpty(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name is required")
    @NotEmpty(message = "Last name is required")
    private String lastName;
    @NotNull(message = "Email is required")
    @Email
    private String email;
    private String phone;
    private String address;
    private Gender gender; // male or female
    private Long departmentId;
    private EmployeeClass employeeClass;

}
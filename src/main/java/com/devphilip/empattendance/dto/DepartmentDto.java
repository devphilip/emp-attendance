package com.devphilip.empattendance.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.devphilip.empattendance.model.Department}
 */
@Data
public class DepartmentDto implements Serializable {
    @NotNull(message = "Please provide the department name")
    @NotEmpty(message = "Please provide the department name")
    String name;
}
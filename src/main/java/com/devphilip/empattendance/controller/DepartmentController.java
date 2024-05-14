package com.devphilip.empattendance.controller;

import com.devphilip.empattendance.model.Department;
import com.devphilip.empattendance.dto.DepartmentDto;
import com.devphilip.empattendance.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<?> addDepartment(@RequestBody @Valid DepartmentDto departmentDto, UriComponentsBuilder urlBuilder) {
        Department department = departmentService.addDepartment(departmentDto);
        UriComponents uriComponents = urlBuilder.path("/departments/{id}").buildAndExpand(department.getId());
        return ResponseEntity.created(uriComponents.toUri()).body("Department added successfully");
    }

    @GetMapping
    public ResponseEntity<?> getDepartments() {
        return ResponseEntity.ok(departmentService.getDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, departmentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Department deleted successfully");
    }


}

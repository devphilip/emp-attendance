package com.devphilip.empattendance.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "attendance")
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime timeIn;
    private LocalTime timeOut;
    private LocalDate date;
    // using month as variable name in this entity class throws error
    private String aMonth;
    private String status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
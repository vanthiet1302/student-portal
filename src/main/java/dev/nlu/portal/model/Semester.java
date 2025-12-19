package dev.nlu.portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Semester {
    private Long id;
    private String semesterCode;  // HK1-2024-2025
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean current;
}
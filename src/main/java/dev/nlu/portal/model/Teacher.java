package dev.nlu.portal.model;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private Long id;
    private Long userId;
    private String teacherCode;
    private String fullName;
    private String email;
    private String phone;
    private String department;  // Khoa/Bộ môn
    private Degree degree;
}
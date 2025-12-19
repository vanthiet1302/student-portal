package dev.nlu.portal.model;

import dev.nlu.portal.service.UserServiceImpl;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long id;
    private Long userId;
    private String studentCode;
    private String fullName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String address;
    private String major; // Ngành học
    private String className; // DH23DTA

    public User getUser() {
        UserServiceImpl userService = new UserServiceImpl();
        return userService.findById(userId);
    }
}
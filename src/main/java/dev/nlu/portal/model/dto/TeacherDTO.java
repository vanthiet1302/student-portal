package dev.nlu.portal.model.dto;

import dev.nlu.portal.model.Degree;
import dev.nlu.portal.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private Long id;
    private String username;
    private Role role;
    private boolean enabled = true; // Trạng thái
    private String teacherCode;
    private String fullName;
    private String email;
    private String phone;
    private String department;  // Khoa/Bộ môn
    private Degree degree;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

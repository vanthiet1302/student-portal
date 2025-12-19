package dev.nlu.portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String passwordHash;
    private Role role;
    private boolean enabled = true; // Trạng thái
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
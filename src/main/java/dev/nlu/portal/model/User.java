package dev.nlu.portal.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String passwordHashed;
    private Role role;
    @Builder.Default
    private boolean enabled = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
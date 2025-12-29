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
    private String id;
    private String username;
    private String hashedPassword;
    private String primaryEmail;
    private String personEmail;
    private Role role;
    @Builder.Default
    private boolean enabled = true;
    private String avatarUrl;
    private String avatarId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt; 
}
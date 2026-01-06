package dev.nlu.portal.model;

import java.time.LocalDateTime;

import dev.nlu.portal.utils.PasswordUtils;
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
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    @Builder.Default
    private boolean enabled = true;
    private String avatarUrl;
    private String avatarId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String authProvider;
    
    public boolean isUsername(String username) {
    	return this.username.equals(username);
    }
    
    public String displayName() {
    	return this.lastName + this.firstName;
    }
    
    public boolean isEmail(String email) {
    	return this.email.equals(email);
    }
}
package dev.nlu.portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassRegistration {
    private Long id;
    private Long studentId;
    private Long classId;
    private LocalDateTime registeredAt;
    private RegistrationStatus status = RegistrationStatus.REGISTERED;
}

enum RegistrationStatus {
    REGISTERED, CANCELLED
}
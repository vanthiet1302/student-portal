package dev.nlu.portal.model;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class User {
    Long userId;
    String primaryEmail; // username
    String passwordHash;
    String firstName;
    String lastName;
    String phoneNumber;
    String citizenId;
    String nation; // Dân tộc
    String religion; // Tôn giáo
    String pob; // Place of birth
    String nationality; // Quốc tịch
    String secondaryEmail;
    String address;
    Role role;
    boolean isMale;
    boolean isActive;
    Date dob;
    LocalDateTime createAt;
    LocalDateTime updateAt;
}

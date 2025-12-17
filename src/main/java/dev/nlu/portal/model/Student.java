package dev.nlu.portal.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Student{
    String id;
    String username;
    String primaryEmail; // username
    String hashPassword;
    String firstname;
    String lastname;
    Date dob;
    boolean isMale;
    String status;
    String phone;
    String citizenId;
    String nation; // Dân tộc
    String religion; // Tôn giáo
    String pob; // Place of birth
    String nationality; // Quốc tịch
    String address;
    LocalDateTime createAt;
    LocalDateTime updateAt;
    String classId;
    String avatarUrl;
}

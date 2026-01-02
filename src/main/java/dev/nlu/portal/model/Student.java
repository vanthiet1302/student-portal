package dev.nlu.portal.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String userId;
    private User user;
    private String lastName;
    private String firstName;
    private LocalDate dob;
    private String pob;
    private String gender;
    @Builder.Default
    private Status status = Status.STUDYING;
    private String phoneNumber;
    private String citizenId;
    private String nation; // Dân tộc
    private String religion; // Tôn giáo
    private String nationality; // Quốc tịch
    private String address; // Địa chỉ
    private String classId;
}
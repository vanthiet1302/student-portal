package dev.nlu.portal.model;

import lombok.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long id;              // ID của bảng students
    private User user;            // Đối tượng User (chứa username, password, role...)
    
    private String studentCode;   // Mã SV
    private String fullName;      // Tên sinh viên
    private LocalDate birthday;   // Ngày sinh
    private String gender;        // Giới tính
    private String status;        // Trạng thái (Đang học,...)
    private String phoneNumber;   // Số điện thoại
    private String identityCard;  // Số CMND/CCCD
    private String ethnicity;     // Dân tộc
    private String religion;      // Tôn giáo
    private String birthPlace;    // Nơi sinh
    private String nationality;   // Quốc tịch
    private String emailPersonal; // Email 2
    private String address;       // Địa chỉ
}
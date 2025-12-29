package dev.nlu.portal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lecturer {
    private String userId; // pk + fk
    private User user;

    private String fullName;      // Họ và tên
    private Integer birthYear;    // Năm sinh
    private String gender;        // Giới tính
    private String identityCard;  // CMND/CCCD

    private String academicRank;  // Học hàm (GS, PGS...)
    private String degree;        // Học vị (Thạc sĩ, Tiến sĩ...)
    private String specialization;// Chuyên ngành
    private String position;      // Chức vụ hành chính

    private String department;    // Tên phòng, khoa, bộ môn
    private String workAgency;    // Tên cơ quan công tác
    private String agencyAddress; // Địa chỉ cơ quan
    private String phoneFixed;    // Điện thoại cố định (đơn vị)
    private String fax;

    private String emailWork;     // Email trường/cơ quan
    private String emailPersonal; // Email cá nhân
    private String phoneMobile;   // Di động

    private String bankAccountNumber; // Số tài khoản
    private String bankName;          // Mở tại ngân hàng
    private String bankBranch;        // Tên chi nhánh
}
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
    private Long id;
    private User user; // Liên kết 1:1 với User (chứa username, password, role)

    // --- Thông tin cá nhân cơ bản ---
    private String fullName;      // Họ và tên
    private Integer birthYear;    // Năm sinh
    private String gender;        // Giới tính
    private String identityCard;  // CMND/CCCD

    // --- Thông tin học thuật ---
    private String academicRank;  // Học hàm (GS, PGS...)
    private String degree;        // Học vị (Thạc sĩ, Tiến sĩ...)
    private String specialization;// Chuyên ngành
    private String position;      // Chức vụ hành chính

    // --- Thông tin công tác ---
    private String department;    // Tên phòng, khoa, bộ môn
    private String workAgency;    // Tên cơ quan công tác
    private String agencyAddress; // Địa chỉ cơ quan
    private String phoneFixed;    // Điện thoại cố định (đơn vị)
    private String fax;

    // --- Thông tin liên lạc cá nhân ---
    private String emailWork;     // Email trường/cơ quan
    private String emailPersonal; // Email cá nhân
    private String phoneMobile;   // Di động

    // --- Thông tin tài khoản ngân hàng ---
    private String bankAccountNumber; // Số tài khoản
    private String bankName;          // Mở tại ngân hàng
    private String bankBranch;        // Tên chi nhánh
}
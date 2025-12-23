package dev.nlu.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeItemDTO {
    private String course;     // Tên môn học
    private int credits;       // Tín chỉ
    private Double mid;        // Điểm giữa kỳ
    private Double finalExam;  // Điểm cuối kỳ
    private Double total;      // Điểm tổng kết
    private String rank;       // Xếp loại (Giỏi/Khá/...)
    private String term;       // Học kỳ
}

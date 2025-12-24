package dev.nlu.portal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleSlot {
    private String dayOfWeek;    // "Thứ 2"
    private String timeStart;    // "07:00"
    private String timeEnd;      // "09:00"
    private String room;         // "A101"
    private String courseName;   // Tên môn
    private String teacherName;  // Tên giảng viên
}
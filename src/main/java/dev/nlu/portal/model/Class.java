package dev.nlu.portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    private Long id;
    private String classCode;
    private Long courseId;
    private Long teacherId;
    private Long semesterId;
    private int maxStudents;
    private int currentStudents;
    private String schedule;  // Ví dụ: "Thứ 2, tiết 1-3, phòng A101"
    private String weekRange;
}
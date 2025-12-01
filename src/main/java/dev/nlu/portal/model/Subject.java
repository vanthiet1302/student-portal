package dev.nlu.portal.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Subject {// Khóa học/ Môn học
    Long courseId;
    String courseName;
    String credits;
    Long teacherId; // FK bảng teacher
}

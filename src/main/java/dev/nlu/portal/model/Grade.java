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
public class Grade {

    Long gradeId;
    Long studentId;
    Long courseId;
    String nienKhoa;
    String departmentId;
}

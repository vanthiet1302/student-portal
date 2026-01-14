package dev.nlu.portal.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AcademicClass {
    String id;
    String code;
    String name;
    String facultyId; // faculty.id
    String advisorId; // lecturer.userId
    String nienKhoa; // 2025-2029
}

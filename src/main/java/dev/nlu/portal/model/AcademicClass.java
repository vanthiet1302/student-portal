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
    String facultyId;
    String advisorId;
}

package dev.nlu.portal.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    String id;
    String code;
    String name;
    int soTinChi;
    int lyThuyet;
    int thucHanh;
    String url;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

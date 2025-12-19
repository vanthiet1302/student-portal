package dev.nlu.portal.model;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {
    private Long id;
    private Long registrationId;
    private Double midtermScore;
    private Double finalScore;
    private Double overallScore;
    private String letterGrade;  // A, B+, C...
}

package dev.nlu.portal.servlet;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Course {
    String id;
    String className;
    String major;
    String department;
    String trainingLevel;
    String schoolYear;
}

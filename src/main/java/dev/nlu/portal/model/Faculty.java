package dev.nlu.portal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {
    private String id;
    private String code;
    private String name;
    private LocalDate establishedDate;
}

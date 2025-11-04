package dev.nlu.portal.servlet;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Student {
    String id; //mssv
    String password;
    String name;
    Date dob;
    boolean sex;
    String numberPhone;
    String citizenId;
    String email;
    String pob;
    String nation;
    String religion;
    String present;
    String householdRegistration;
}

package ru.spbstu.courseworkserver.main.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class PersonnelDto {
    private String firstName;
    private String lastName;
    private String patherName;

    public PersonnelDto(String firstName, String lastName, String patherName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
    }
}

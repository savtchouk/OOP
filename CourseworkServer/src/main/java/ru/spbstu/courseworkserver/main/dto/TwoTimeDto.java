package ru.spbstu.courseworkserver.main.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@JsonSerialize
public class TwoTimeDto {
    private Timestamp FisrtTime;
    private Timestamp SecondTime;

    public TwoTimeDto(Timestamp fisrtTime, Timestamp secondTime) {
        FisrtTime = fisrtTime;
        SecondTime = secondTime;
    }
}

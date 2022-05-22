package ru.spbstu.courseworkserver.main.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@JsonSerialize
public class TimeDto {
    private Timestamp time;

    public TimeDto(Timestamp time) {
        this.time = time;
    }
}

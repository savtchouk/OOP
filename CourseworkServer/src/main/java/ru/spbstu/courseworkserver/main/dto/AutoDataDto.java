package ru.spbstu.courseworkserver.main.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class AutoDataDto {
    private String color;
    private String mark;

    public AutoDataDto(String color, String mark) {
        this.color = color;
        this.mark = mark;
    }
}

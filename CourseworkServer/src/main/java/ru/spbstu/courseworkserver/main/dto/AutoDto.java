package ru.spbstu.courseworkserver.main.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class AutoDto {
    private String num;
    private String color;
    private String mark;
    private Integer personnelId;

    public AutoDto(String num, String color, String mark, Integer personnelId) {
        this.num = num;
        this.color = color;
        this.mark = mark;
        this.personnelId = personnelId;
    }
}

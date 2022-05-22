package ru.spbstu.courseworkserver.main.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class RouteDto {
    private Integer id;
    private String name;

    public RouteDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}

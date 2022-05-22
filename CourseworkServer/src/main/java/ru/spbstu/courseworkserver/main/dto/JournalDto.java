package ru.spbstu.courseworkserver.main.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@JsonSerialize
public class JournalDto {
    private Timestamp timeOut;
    private Timestamp timeIn;
    private Integer autoId;
    private Integer routeId;

    public JournalDto(Timestamp timeOut, Timestamp timeIn, Integer autoId, Integer routeId) {
        this.timeOut = timeOut;
        this.timeIn = timeIn;
        this.autoId = autoId;
        this.routeId = routeId;
    }

    @Override
    public String toString() {
        return "JournalDto{" +
                "timeOut=" + timeOut +
                ", timeIn=" + timeIn +
                ", autoId=" + autoId +
                ", routeId=" + routeId +
                '}';
    }
}

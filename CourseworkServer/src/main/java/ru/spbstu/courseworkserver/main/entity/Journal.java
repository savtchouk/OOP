package ru.spbstu.courseworkserver.main.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "Journal")
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "time_out", precision = 3, nullable = false)
    private Timestamp timeOut;

    @Column(name = "time_in", precision = 3, nullable = false)
    private Timestamp timeIn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auto_id")
    private Auto auto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id")
    private Route route;

    public Journal() {
    }

    public Journal(Timestamp timeOut, Timestamp timeIn, Auto auto, Route route) {
        this.timeOut = timeOut;
        this.timeIn = timeIn;
        this.auto = auto;
        this.route = route;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", timeOut=" + timeOut +
                ", timeIn=" + timeIn +
                ", auto=" + auto +
                ", route=" + route +
                '}';
    }
}

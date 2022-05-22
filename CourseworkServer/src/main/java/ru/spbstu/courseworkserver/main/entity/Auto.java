package ru.spbstu.courseworkserver.main.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "Auto")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "num", length = 20, nullable = false, unique = true)
    private String num;

    @Column(name = "color", length = 20, nullable = false)
    private String color;

    @Column(name = "mark", length = 20, nullable = false)
    private String mark;

    @JsonIgnore
    @OneToMany(mappedBy = "auto", cascade = CascadeType.ALL)
    private Collection<Journal> journals;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personnel_id")
    private AutoPersonnel autoPersonnel;

    public Auto() {
    }

    public Auto(String num, String color, String mark, AutoPersonnel autoPersonnel) {
        this.num = num;
        this.color = color;
        this.mark = mark;
        this.autoPersonnel = autoPersonnel;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "id=" + id +
                ", num='" + num + '\'' +
                ", color='" + color + '\'' +
                ", mark='" + mark + '\'' +
                ", autoPersonnel=" + autoPersonnel +
                '}';
    }
}

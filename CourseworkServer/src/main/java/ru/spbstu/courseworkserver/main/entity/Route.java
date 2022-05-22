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
@Table(name = "Routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private Collection<Journal> journals;

    public Route() {
    }

    public Route(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

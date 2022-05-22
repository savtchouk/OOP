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
@Table(name = "Auto_personnel")
public class AutoPersonnel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;

    @Column(name = "pather_name", length = 20, nullable = false)
    private String patherName;

    @JsonIgnore
    @OneToMany(mappedBy = "autoPersonnel", cascade = CascadeType.ALL)
    private Collection<Auto> autos;

    public AutoPersonnel() {
    }

    public AutoPersonnel(String firstName, String lastName, String patherName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
    }

    @Override
    public String toString() {
        return "AutoPersonnel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patherName='" + patherName + '\'' +
                '}';
    }
}

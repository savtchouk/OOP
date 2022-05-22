package ru.spbstu.courseworkserver.main.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.spbstu.courseworkserver.main.entity.Auto;

import javax.transaction.Transactional;
import java.util.Optional;

public interface AutoRepository extends CrudRepository<Auto, Integer> {
    Optional<Auto> findAutoByNum(String num);
    Iterable<Auto> findAutosByColor(String color);
    Iterable<Auto> findAutosByMark(String mark);

    @Query("SELECT a FROM Auto a " +
            "JOIN AutoPersonnel ap " +
            "ON a.autoPersonnel = ap " +
            "WHERE ap.id = :id")
    Iterable<Auto> findAutosByPersonnelId(@Param("id") Integer id);

    @Query("SELECT a FROM Auto a " +
            "JOIN AutoPersonnel ap " +
            "ON a.autoPersonnel = ap " +
            "WHERE ap.lastName = :lastName")
    Iterable<Auto> findAutosByPersonnelLastName(@Param("lastName") String lastName);
}

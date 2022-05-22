package ru.spbstu.courseworkserver.main.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.spbstu.courseworkserver.main.entity.Journal;

import java.sql.Timestamp;
import java.util.Optional;

public interface JournalRepository extends CrudRepository<Journal, Integer> {

    @Query("SELECT j FROM Journal j " +
            "JOIN Auto a " +
            "ON j.auto = a " +
            "WHERE a.id = :id")
    Iterable<Journal> findJournalsByAutoId(@Param("id") Integer id);

    @Query("SELECT j FROM Journal j " +
            "JOIN Route r " +
            "ON j.route = r " +
            "WHERE r.id = :id")
    Iterable<Journal> findJournalsByRouteId(@Param("id") Integer id);

    @Query("SELECT j FROM Journal j " +
            "JOIN Auto a " +
            "ON j.auto = a " +
            "WHERE a.num = :num")
    Iterable<Journal> findJournalsByAutoNum(
            @Param("num") String num
    );

    @Query("SELECT j FROM Journal j " +
            "JOIN Auto a " +
            "ON j.auto = a " +
            "WHERE a.color = :color " +
            "AND a.mark = :mark")
    Iterable<Journal> findJournalsByAutoColorAndMark(
            @Param("color") String color,
            @Param("mark") String mark
    );

    @Query("SELECT j FROM Journal j " +
            "JOIN Route r " +
            "ON j.route = r " +
            "WHERE r.name = :routeName")
    Iterable<Journal> findJournalsByRouteName(
            @Param("routeName") String routeName
    );

    Iterable<Journal> findJournalsByTimeInBetween(
            Timestamp firstTimeIn, Timestamp secondTimeIn
    );

    Iterable<Journal> findJournalsByTimeInBefore(Timestamp timeIn);
}

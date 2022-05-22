package ru.spbstu.courseworkserver.main.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.spbstu.courseworkserver.main.entity.AutoPersonnel;

import javax.transaction.Transactional;

public interface PersonnelRepository extends CrudRepository<AutoPersonnel, Integer> {
    @Query("SELECT ap " +
            "FROM AutoPersonnel ap " +
            "WHERE ap.firstName = :firstName " +
            "AND ap.lastName = :lastName " +
            "AND ap.patherName = :patherName")
    Iterable<AutoPersonnel> findPersonnelByName(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("patherName") String patherName
    );

    @Modifying
    @Transactional
    @Query("UPDATE AutoPersonnel ap " +
            "SET ap.firstName = :newFirstName, " +
            "ap.lastName = :newLastName," +
            "ap.patherName = :newPatherName " +
            "WHERE ap.id = :id")
    int updatePersonnelName(
            @Param("id") Integer id,
            @Param("newFirstName") String newFirstName,
            @Param("newLastName") String newLastName,
            @Param("newPatherName") String newPatherName
    );
}

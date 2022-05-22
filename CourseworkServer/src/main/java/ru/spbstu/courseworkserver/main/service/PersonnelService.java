package ru.spbstu.courseworkserver.main.service;

import ru.spbstu.courseworkserver.main.dto.PersonnelDto;
import ru.spbstu.courseworkserver.main.entity.AutoPersonnel;

import java.util.List;

public interface PersonnelService {
    AutoPersonnel findById(int id);
    List<AutoPersonnel> findAll();
    void add(AutoPersonnel personnel);
    void deleteById(Integer id);
    List<AutoPersonnel> findByName(PersonnelDto personnelDto);
    AutoPersonnel updateNameById(Integer id, PersonnelDto newPersonnel);
}

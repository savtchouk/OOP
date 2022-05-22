package ru.spbstu.courseworkserver.main.service;

import ru.spbstu.courseworkserver.main.dto.AutoDto;
import ru.spbstu.courseworkserver.main.dto.PersonnelDto;
import ru.spbstu.courseworkserver.main.entity.Auto;

import java.util.List;

public interface AutoService {
    Auto findById(int id);
    List<Auto> findAll();
    List<Auto> findByPersonnelId(Integer id);
    Auto add(AutoDto autoDto);
    void deleteById(Integer id);
    Auto updateDataById(Integer id, Auto newAuto);

    Auto findByNum(String num);
    List<Auto> findByColor(String color);
    List<Auto> findByMark(String mark);

    List<Auto> findByPersonnelLastName(String lastName);
}

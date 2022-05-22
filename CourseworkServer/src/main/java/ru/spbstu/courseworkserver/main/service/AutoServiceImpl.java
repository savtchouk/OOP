package ru.spbstu.courseworkserver.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spbstu.courseworkserver.main.dto.AutoDto;
import ru.spbstu.courseworkserver.main.dto.PersonnelDto;
import ru.spbstu.courseworkserver.main.entity.Auto;
import ru.spbstu.courseworkserver.main.entity.AutoPersonnel;
import ru.spbstu.courseworkserver.main.exception.AutoNotFoundException;
import ru.spbstu.courseworkserver.main.repository.AutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AutoServiceImpl implements AutoService {
    @Autowired private AutoRepository autoRepository;
    @Autowired private PersonnelService personnelService;

    @Override
    public Auto findById(int id) {
        Optional<Auto> auto = autoRepository.findById(id);
        if (auto.isPresent()) {
            return auto.get();
        } else {
            throw new AutoNotFoundException("auto not found");
        }
    }

    @Override
    public List<Auto> findAll() {
        return (List<Auto>) autoRepository.findAll();
    }

    @Override
    public List<Auto> findByPersonnelId(Integer id) {
        personnelService.findById(id);
        return (List<Auto>) autoRepository.findAutosByPersonnelId(id);
    }

    @Override
    public Auto add(AutoDto autoDto) {
        String num = autoDto.getNum();
        String color = autoDto.getColor();
        String mark = autoDto.getMark();
        Integer personnelId = autoDto.getPersonnelId();

        AutoPersonnel personnel = personnelService.findById(personnelId);
        Auto auto = new Auto(num, color, mark, personnel);
        autoRepository.save(auto);
        return auto;
    }

    @Override
    public void deleteById(Integer id) {
        Auto auto = findById(id);
        autoRepository.delete(auto);
    }

    @Override
    public Auto updateDataById(Integer id, Auto newAuto) {
        Auto auto = findById(id);
        auto.setNum(newAuto.getNum());
        auto.setColor(newAuto.getColor());
        auto.setMark(newAuto.getMark());
        autoRepository.save(auto);
        return auto;
    }

    @Override
    public Auto findByNum(String num) {
        Optional<Auto> auto = autoRepository.findAutoByNum(num);
        if (auto.isPresent()) {
            return auto.get();
        } else {
            throw new AutoNotFoundException("Auto not found");
        }
    }

    @Override
    public List<Auto> findByColor(String color) {
        return (List<Auto>) autoRepository.findAutosByColor(color);
    }

    @Override
    public List<Auto> findByMark(String mark) {
        return (List<Auto>) autoRepository.findAutosByMark(mark);
    }

    @Override
    public List<Auto> findByPersonnelLastName(String lastName)
    {
        return (List<Auto>) autoRepository.findAutosByPersonnelLastName(lastName);
    }
}

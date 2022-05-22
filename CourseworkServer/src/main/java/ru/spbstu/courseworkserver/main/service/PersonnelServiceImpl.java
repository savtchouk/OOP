package ru.spbstu.courseworkserver.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spbstu.courseworkserver.main.dto.PersonnelDto;
import ru.spbstu.courseworkserver.main.entity.AutoPersonnel;
import ru.spbstu.courseworkserver.main.exception.PersonnelNotFoundException;
import ru.spbstu.courseworkserver.main.repository.PersonnelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonnelServiceImpl implements PersonnelService {
    @Autowired private PersonnelRepository personnelRepository;

    @Override
    public AutoPersonnel findById(int id) {
        Optional<AutoPersonnel> personnel = personnelRepository.findById(id);
        if (personnel.isPresent()) {
            return personnel.get();
        } else {
            throw new PersonnelNotFoundException("personnel not found");
        }
    }

    @Override
    public List<AutoPersonnel> findAll() {
        return (List<AutoPersonnel>) personnelRepository.findAll();
    }

    @Override
    public void add(AutoPersonnel personnel) {
        personnelRepository.save(personnel);
    }

    @Override
    public void deleteById(Integer id) {
        AutoPersonnel personnel = findById(id);
        personnelRepository.delete(personnel);
    }

    @Override
    public List<AutoPersonnel> findByName(PersonnelDto personnelDto) {
        return (List<AutoPersonnel>) personnelRepository.findPersonnelByName(
                personnelDto.getFirstName(),
                personnelDto.getLastName(),
                personnelDto.getPatherName()
        );
    }

    @Override
    public AutoPersonnel updateNameById(Integer id, PersonnelDto newPersonnel) {
        personnelRepository.updatePersonnelName(
                id,
                newPersonnel.getFirstName(),
                newPersonnel.getLastName(),
                newPersonnel.getPatherName()
        );
        AutoPersonnel personnel = findById(id);
        return personnel;
    }
}

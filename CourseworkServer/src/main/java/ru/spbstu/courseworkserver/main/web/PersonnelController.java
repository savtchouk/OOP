package ru.spbstu.courseworkserver.main.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.spbstu.courseworkserver.main.dto.PersonnelDto;
import ru.spbstu.courseworkserver.main.entity.AutoPersonnel;
import ru.spbstu.courseworkserver.main.exception.PersonnelNotFoundException;
import ru.spbstu.courseworkserver.main.service.PersonnelService;

import java.util.List;

@RestController
@RequestMapping("/personnels")
public class PersonnelController {
    @Autowired private PersonnelService personnelService;

    @GetMapping("/find/{id}")
    public ResponseEntity<AutoPersonnel> findById(@PathVariable("id") int id) {
        try {
            AutoPersonnel personnel = personnelService.findById(id);
            return new ResponseEntity<>(personnel, HttpStatus.OK);
        } catch (PersonnelNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<AutoPersonnel>> findAll() {
        List<AutoPersonnel> personnels = personnelService.findAll();
        return new ResponseEntity<>(personnels, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        try {
            personnelService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (PersonnelNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(
            value = "/add",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<AutoPersonnel> add(@RequestBody AutoPersonnel personnel) {
        personnelService.add(personnel);
        return new ResponseEntity<>(personnel, HttpStatus.OK);
    }

    @PutMapping(
            value = "/update/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<AutoPersonnel> updateNameById(
            @PathVariable("id") Integer id,
            @RequestBody PersonnelDto newPersonnel
    ) {
        try {
            AutoPersonnel personnel = personnelService.updateNameById(id, newPersonnel);
            return new ResponseEntity<>(personnel, HttpStatus.OK);
        } catch (PersonnelNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(
            value = "/find-by-name",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<List<AutoPersonnel>> findByName(@RequestBody PersonnelDto personnelDto) {
        List<AutoPersonnel> personnels = personnelService.findByName(personnelDto);
        return new ResponseEntity<>(personnels, HttpStatus.OK);
    }
}

package ru.spbstu.courseworkserver.main.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.spbstu.courseworkserver.main.dto.AutoDto;
import ru.spbstu.courseworkserver.main.dto.PersonnelDto;
import ru.spbstu.courseworkserver.main.entity.Auto;
import ru.spbstu.courseworkserver.main.exception.AutoNotFoundException;
import ru.spbstu.courseworkserver.main.exception.PersonnelNotFoundException;
import ru.spbstu.courseworkserver.main.service.AutoService;

import java.util.List;

@RestController
@RequestMapping("/autos")
public class AutoController {
    @Autowired private AutoService autoService;

    @GetMapping("/find/{id}")
    public ResponseEntity<Auto> findById(@PathVariable("id") int id) {
        try {
            Auto auto = autoService.findById(id);
            return new ResponseEntity<>(auto, HttpStatus.OK);
        } catch (AutoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Auto>> findAll() {
        List<Auto> autos = autoService.findAll();
        return new ResponseEntity<>(autos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        try {
            autoService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (AutoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(
            value = "/add",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Auto> add(@RequestBody AutoDto autoDto) {
        try {
            Auto auto = autoService.add(autoDto);
            return new ResponseEntity<>(auto, HttpStatus.OK);
        } catch (PersonnelNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping(
            value = "/update/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Auto> updateById(
            @PathVariable("id") Integer id,
            @RequestBody Auto newAuto
    ) {
        try {
            Auto auto = autoService.updateDataById(id, newAuto);
            return new ResponseEntity<>(auto, HttpStatus.OK);
        } catch (AutoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find-by-personnel/{id}")
    public ResponseEntity<List<Auto>> findByPersonnelId(@PathVariable("id") Integer id) {
        try {
            List<Auto> autos = autoService.findByPersonnelId(id);
            return new ResponseEntity<>(autos, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find-by-num/{num}")
    public ResponseEntity<Auto> findByNum(@PathVariable("num") String num) {
        try {
            Auto auto = autoService.findByNum(num);
            return new ResponseEntity<>(auto, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find-by-color/{color}")
    public ResponseEntity<List<Auto>> findByColor(@PathVariable("color") String color) {
        List<Auto> autos = autoService.findByColor(color);
        return new ResponseEntity<>(autos, HttpStatus.OK);
    }

    @GetMapping("/find-by-mark/{mark}")
    public ResponseEntity<List<Auto>> findByMark(@PathVariable("mark") String mark) {
        List<Auto> autos = autoService.findByMark(mark);
        return new ResponseEntity<>(autos, HttpStatus.OK);
    }

    @GetMapping("/find-by-personnel-last-name/{lastName}")
    public ResponseEntity<List<Auto>> findByPersonnelLastName(
            @PathVariable("lastName") String lastName
    ) {
        List<Auto> autos = autoService.findByPersonnelLastName(lastName);
        return new ResponseEntity<>(autos, HttpStatus.OK);
    }
}

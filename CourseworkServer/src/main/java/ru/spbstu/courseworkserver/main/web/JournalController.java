package ru.spbstu.courseworkserver.main.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.spbstu.courseworkserver.main.dto.AutoDataDto;
import ru.spbstu.courseworkserver.main.dto.JournalDto;
import ru.spbstu.courseworkserver.main.dto.TimeDto;
import ru.spbstu.courseworkserver.main.dto.TwoTimeDto;
import ru.spbstu.courseworkserver.main.entity.Journal;
import ru.spbstu.courseworkserver.main.exception.AutoNotFoundException;
import ru.spbstu.courseworkserver.main.exception.JournalNotFoundException;
import ru.spbstu.courseworkserver.main.exception.RouteNotFoundException;
import ru.spbstu.courseworkserver.main.service.JournalService;

import java.util.List;

@RestController
@RequestMapping("/journals")
public class JournalController {
    @Autowired private JournalService journalService;

    @GetMapping("/find/{id}")
    public ResponseEntity<Journal> findById(@PathVariable("id") int id) {
        try {
            Journal journal = journalService.findById(id);
            return new ResponseEntity<>(journal, HttpStatus.OK);
        } catch (JournalNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Journal>> findAll() {
        List<Journal> journals = journalService.findAll();
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        try {
            journalService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (JournalNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(
            value = "/add",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Journal> add(@RequestBody JournalDto journalDto) {
        try {
            System.out.println(journalDto);
            Journal journal = journalService.add(journalDto);
            return new ResponseEntity<>(journal, HttpStatus.OK);
        } catch (AutoNotFoundException | RouteNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping(
            value = "/update/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Journal> updateTimeById(
            @PathVariable("id") Integer id,
            @RequestBody Journal newJournal
    ) {
        try {
            Journal journal = journalService.updateTimeById(id, newJournal);
            return new ResponseEntity<>(journal, HttpStatus.OK);
        } catch (JournalNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find-by-auto/{id}")
    public ResponseEntity<List<Journal>> findByAutoId(@PathVariable("id") Integer id) {
        try {
            List<Journal> journals = journalService.findByAutoId(id);
            return new ResponseEntity<>(journals, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find-by-route/{id}")
    public ResponseEntity<List<Journal>> findByRouteId(@PathVariable("id") Integer id) {
        try {
            List<Journal> journals = journalService.findByRouteId(id);
            return new ResponseEntity<>(journals, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find-by-auto-num/{num}")
    public ResponseEntity<List<Journal>> findByAutoNum(@PathVariable("num") String num) {
        List<Journal> journals = journalService.findByAutoNum(num);
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @GetMapping("/find-by-route-name/{name}")
    public ResponseEntity<List<Journal>> findByRouteName(@PathVariable("name") String name) {
        List<Journal> journals = journalService.findByRouteName(name);
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @GetMapping(
            value = "/find-time-in-before",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<List<Journal>> findByTimeInBetween(@RequestBody TwoTimeDto timeDto) {
        List<Journal> journals = journalService.findByTimeInBetween(timeDto);
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @GetMapping(
        value = "/find-time-in-between",
        consumes = "application/json",
        produces = "application/json"
    )
    public ResponseEntity<List<Journal>> findByTimeInBefore(@RequestBody TimeDto timeIn) {
        List<Journal> journals = journalService.findByTimeInBefore(timeIn);
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }
}

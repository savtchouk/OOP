package ru.spbstu.courseworkserver.main.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.spbstu.courseworkserver.main.entity.Route;
import ru.spbstu.courseworkserver.main.exception.RouteNotFoundException;
import ru.spbstu.courseworkserver.main.service.RouteService;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {
    @Autowired private RouteService routeService;

    @GetMapping("/find/{id}")
    public ResponseEntity<Route> findById(@PathVariable("id") int id) {
        try {
            Route route = routeService.findById(id);
            return new ResponseEntity<>(route, HttpStatus.OK);
        } catch (RouteNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Route>> findAll() {
        List<Route> routes = routeService.findAll();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        try {
            routeService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (RouteNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(
            value = "/add",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Route> add(@RequestBody Route route) {
        routeService.add(route);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @PutMapping(
            value = "/update/{id}",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Route> deleteNameById(
            @PathVariable("id") Integer id,
            @RequestBody String name
    ) {
        try {
            Route route = routeService.updateNameById(id, name);
            return new ResponseEntity<>(route, HttpStatus.OK);
        } catch (RouteNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<Route>> findByName(@PathVariable("name") String name) {
        List<Route> routes = routeService.findByName(name);
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping("/find-first-by-name/{name}")
    public ResponseEntity<Route> findFirstByName(@PathVariable("name") String name) {
        try {
            Route route = routeService.findFirstByName(name);
            return new ResponseEntity<>(route, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find-starting-with/infix={infix}")
    public ResponseEntity<List<Route>> findStartingWith(@PathVariable("infix") String infix) {
        List<Route> routes = routeService.findByNameStartingWith(infix);
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @DeleteMapping("/delete-starting-with/infix={infix}")
    public ResponseEntity deleteStartingWith(@PathVariable("infix") String infix) {
        routeService.deleteByNameStartingWith(infix);
        return new ResponseEntity(HttpStatus.OK);
    }
}

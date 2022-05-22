package ru.spbstu.courseworkserver.main.service;

import ru.spbstu.courseworkserver.main.entity.Route;

import java.util.List;

public interface RouteService {
    Route findById(int id);
    List<Route> findAll();
    void add(Route route);
    void deleteById(Integer id);
    Route updateNameById(Integer id, String newRoute);

    List<Route> findByName(String name);
    Route findFirstByName(String name);
    List<Route> findByNameStartingWith(String infix);

    void deleteByNameStartingWith(String infix);
}

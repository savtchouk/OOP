package ru.spbstu.courseworkserver.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spbstu.courseworkserver.main.entity.Route;
import ru.spbstu.courseworkserver.main.exception.RouteNotFoundException;
import ru.spbstu.courseworkserver.main.repository.RouteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired  private RouteRepository routeRepository;

    @Override
    public Route findById(int id) {
        Optional<Route> route = routeRepository.findById(id);
        if (route.isPresent()) {
            return route.get();
        } else {
            throw new RouteNotFoundException("route not found");
        }
    }

    @Override
    public List<Route> findAll() {
        return (List<Route>) routeRepository.findAll();
    }

    @Override
    public void add(Route route) {
        routeRepository.save(route);
    }

    @Override
    public void deleteById(Integer id) {
        Route route = findById(id);
        routeRepository.delete(route);
    }

    @Override
    public Route updateNameById(Integer id, String newRoute) {
        Route route = findById(id);
        route.setName(newRoute);
        routeRepository.save(route);
        return route;
    }

    @Override
    public List<Route> findByName(String name) {
        return (List<Route>) routeRepository.findRoutesByName(name);
    }

    @Override
    public Route findFirstByName(String name) {
        Optional<Route> route = routeRepository.findFirstRouteByName(name);
        if (route.isPresent()) {
            return route.get();
        } else {
            throw new RouteNotFoundException("route not found");
        }
    }

    @Override
    public List<Route> findByNameStartingWith(String infix) {
        return (List<Route>) routeRepository.findRoutesByNameStartingWith(infix);
    }

    @Override
    public void deleteByNameStartingWith(String infix) {
        routeRepository.deleteRoutesByNameStartingWith(infix);
    }
}

package ru.spbstu.courseworkserver.main.repository;

import org.springframework.data.repository.CrudRepository;
import ru.spbstu.courseworkserver.main.entity.Route;

import javax.transaction.Transactional;
import java.util.Optional;

public interface RouteRepository extends CrudRepository<Route, Integer> {
    void deleteById(Integer id);
    Iterable<Route> findRoutesByName(String name);

    Optional<Route> findFirstRouteByName(String name);

    Iterable<Route> findRoutesByNameStartingWith(String infix);

    @Transactional
    void deleteRoutesByNameStartingWith(String infix);
}

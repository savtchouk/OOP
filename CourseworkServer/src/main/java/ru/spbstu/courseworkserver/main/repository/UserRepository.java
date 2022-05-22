package ru.spbstu.courseworkserver.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spbstu.courseworkserver.main.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findUserByName(String userName);
}

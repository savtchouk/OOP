package ru.spbstu.courseworkserver.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.spbstu.courseworkserver.main.repository.AutoRepository;
import ru.spbstu.courseworkserver.main.repository.JournalRepository;
import ru.spbstu.courseworkserver.main.repository.PersonnelRepository;
import ru.spbstu.courseworkserver.main.repository.RouteRepository;

@SpringBootApplication
public class CourseworkServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(CourseworkServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CourseworkServerApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    };

    @Bean
    public CommandLineRunner test(
            JournalRepository journalRepository,
            RouteRepository routeRepository,
            AutoRepository autoRepository,
            PersonnelRepository personnelRepository
    ) {
        return args -> {
        };
    }
}

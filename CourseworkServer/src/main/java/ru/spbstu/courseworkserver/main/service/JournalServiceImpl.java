package ru.spbstu.courseworkserver.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spbstu.courseworkserver.main.dto.AutoDataDto;
import ru.spbstu.courseworkserver.main.dto.JournalDto;
import ru.spbstu.courseworkserver.main.dto.TimeDto;
import ru.spbstu.courseworkserver.main.dto.TwoTimeDto;
import ru.spbstu.courseworkserver.main.entity.Auto;
import ru.spbstu.courseworkserver.main.entity.Journal;
import ru.spbstu.courseworkserver.main.entity.Route;
import ru.spbstu.courseworkserver.main.exception.JournalNotFoundException;
import ru.spbstu.courseworkserver.main.repository.JournalRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService {
    @Autowired private JournalRepository journalRepository;
    @Autowired private AutoService autoService;
    @Autowired private RouteService routeService;

    @Override
    public Journal findById(int id) {
        Optional<Journal> journal = journalRepository.findById(id);
        return journal.orElseThrow(() -> new JournalNotFoundException("Journal not found"));
    }

    @Override
    public List<Journal> findAll() {
        return (List<Journal>) journalRepository.findAll();
    }

    @Override
    public List<Journal> findByAutoId(Integer id) {
        autoService.findById(id);
        return (List<Journal>) journalRepository.findJournalsByAutoId(id);
    }

    @Override
    public List<Journal> findByRouteId(Integer id) {
        routeService.findById(id);
        return (List<Journal>) journalRepository.findJournalsByRouteId(id);
    }

    @Override
    public Journal add(JournalDto journalDto) {
        Timestamp timeOut = journalDto.getTimeOut();
        Timestamp timeIn = journalDto.getTimeIn();
        Integer autoId = journalDto.getAutoId();
        Integer routeId = journalDto.getRouteId();

        Auto auto = autoService.findById(autoId);
        Route route = routeService.findById(routeId);
        Journal journal = new Journal(timeOut, timeIn, auto, route);
        journalRepository.save(journal);
        return journal;
    }

    @Override
    public void deleteById(Integer id) {
        Journal journal = findById(id);
        journalRepository.delete(journal);
    }

    @Override
    public Journal updateTimeById(Integer id, Journal newJournal) {
        Journal journal = findById(id);
        journal.setTimeOut(newJournal.getTimeOut());
        journal.setTimeIn(newJournal.getTimeIn());
        journalRepository.save(journal);
        return journal;
    }

    @Override
    public List<Journal> findByAutoNum(String num) {
        return (List<Journal>) journalRepository.findJournalsByAutoNum(num);
    }

    @Override
    public List<Journal> findByAutoColorAndMark(AutoDataDto autoDto) {
        return (List<Journal>) journalRepository.findJournalsByAutoColorAndMark(
                autoDto.getColor(),
                autoDto.getMark()
        );
    }

    @Override
    public List<Journal> findByRouteName(String name) {
        return (List<Journal>) journalRepository.findJournalsByRouteName(name);
    }

    @Override
    public List<Journal> findByTimeInBetween(TwoTimeDto timeDto) {
        return (List<Journal>) journalRepository.findJournalsByTimeInBetween(
                timeDto.getFisrtTime(),
                timeDto.getSecondTime()
        );
    }

    @Override
    public List<Journal> findByTimeInBefore(TimeDto timeIn) {
        return (List<Journal>) journalRepository.findJournalsByTimeInBefore(
                timeIn.getTime()
        );
    }
}

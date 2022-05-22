package ru.spbstu.courseworkserver.main.service;

import ru.spbstu.courseworkserver.main.dto.AutoDataDto;
import ru.spbstu.courseworkserver.main.dto.JournalDto;
import ru.spbstu.courseworkserver.main.dto.TimeDto;
import ru.spbstu.courseworkserver.main.dto.TwoTimeDto;
import ru.spbstu.courseworkserver.main.entity.Journal;

import java.util.List;

public interface JournalService {
    Journal findById(int id);
    List<Journal> findAll();
    List<Journal> findByAutoId(Integer id);
    List<Journal> findByRouteId(Integer id);
    Journal add(JournalDto journalDto);
    void deleteById(Integer id);
    Journal updateTimeById(Integer id, Journal newJournal);
    List<Journal> findByAutoNum(String num);
    List<Journal> findByAutoColorAndMark(AutoDataDto autoDto);
    List<Journal> findByRouteName(String name);

    List<Journal> findByTimeInBetween(TwoTimeDto timeDto);
    List<Journal> findByTimeInBefore(TimeDto timeIn);
}

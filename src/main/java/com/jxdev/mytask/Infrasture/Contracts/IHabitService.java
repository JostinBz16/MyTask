package com.jxdev.mytask.Infrasture.Contracts;

import com.jxdev.mytask.Models.HabitDTO;

import java.util.List;
import java.util.Optional;

public interface IHabitService {

    HabitDTO createHabit(HabitDTO habitDTO);

    HabitDTO updateHabit(Long id, HabitDTO habitDTO);

    void deleteHabit(Long id);

    Optional<HabitDTO> getHabitById(Long id);

    List<HabitDTO> getAllHabitsByUser(Long userId);

    List<HabitDTO> getAllHabits();

    Optional<HabitDTO> getHabitByName(String name);

}

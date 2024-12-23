package com.jxdev.mytask.Infrasture.Repositories;

import com.jxdev.mytask.Entities.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IHabitRepository extends JpaRepository<Habit, Long> {
    Optional<Habit> findByName(String name);
    List<Habit> findByUserId(Long userId);

}

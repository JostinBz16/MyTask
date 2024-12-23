package com.jxdev.mytask.Infrasture.Repositories;

import com.jxdev.mytask.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitle(String title);
    List<Task> findByHabitId(Long habitId);
}

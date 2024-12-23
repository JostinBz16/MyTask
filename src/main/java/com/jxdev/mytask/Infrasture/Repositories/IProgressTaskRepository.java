package com.jxdev.mytask.Infrasture.Repositories;

import com.jxdev.mytask.Entities.ProgressTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IProgressTaskRepository extends JpaRepository<ProgressTask, Long> {
    List<ProgressTask> findByDate(LocalDate date);
    Optional<ProgressTask> findByTaskIdAndDate(Long id, LocalDate date);
    List<ProgressTask> findByTaskId(Long id);
}

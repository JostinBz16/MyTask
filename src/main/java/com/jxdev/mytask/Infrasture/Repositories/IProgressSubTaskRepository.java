package com.jxdev.mytask.Infrasture.Repositories;

import com.jxdev.mytask.Entities.ProgressSubTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IProgressSubTaskRepository extends JpaRepository<ProgressSubTask, Long> {
    List<ProgressSubTask> findByDate(LocalDate date);
}

package com.jxdev.mytask.Infrasture.Repositories;

import com.jxdev.mytask.Entities.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISubTaskRepository extends JpaRepository<SubTask, Long> {
    List<SubTask> findByTaskId(Long taskId);
    Optional<SubTask> findByTitle(String title);
}

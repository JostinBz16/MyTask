package com.jxdev.mytask.Infrasture.Contracts;

import com.jxdev.mytask.Models.ProgressSubTaskDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IProgressSubTaskService {
    ProgressSubTaskDTO createProgress(ProgressSubTaskDTO progressDTO);

    ProgressSubTaskDTO updateProgress(Long id, ProgressSubTaskDTO progressDTO);

    Optional<ProgressSubTaskDTO> getProgressById(Long id);

    List<ProgressSubTaskDTO> getProgressByTask(Long taskId);

    List<ProgressSubTaskDTO> getProgressBySubTaskId(Long subTaskId);

    List<ProgressSubTaskDTO> getAllProgressByDate(LocalDate date);

    void deleteProgress(Long id);
}

package com.jxdev.mytask.Infrasture.Contracts;

import com.jxdev.mytask.Models.ProgressTaskDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IProgressTaskService {
    ProgressTaskDTO createProgress(ProgressTaskDTO progressDTO);

    ProgressTaskDTO updateProgress(Long id, ProgressTaskDTO progressDTO);

    Optional<ProgressTaskDTO> getProgressById(Long id);

    List<ProgressTaskDTO> getProgressByTask(Long taskId);

    List<ProgressTaskDTO> getProgressBySubTask(Long subTaskId);

    List<ProgressTaskDTO> getAllProgressByDate(LocalDate date);

    void deleteProgress(Long id);
}

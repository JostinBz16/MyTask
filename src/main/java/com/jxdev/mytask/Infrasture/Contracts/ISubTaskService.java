package com.jxdev.mytask.Infrasture.Contracts;

import com.jxdev.mytask.Models.SubTaskDTO;

import java.util.List;
import java.util.Optional;

public interface ISubTaskService {
    SubTaskDTO createSubTask(SubTaskDTO subTaskDTO);

    SubTaskDTO updateSubTask(Long id, SubTaskDTO subTaskDTO);

    void deleteSubTask(Long id);

    Optional<SubTaskDTO> getSubTaskById(Long id);

    Optional<SubTaskDTO> getSubTaskByName(String name);

    List<SubTaskDTO> getAllSubTasksByTask(Long taskId);

    List<SubTaskDTO> getAllSubTasks();
}

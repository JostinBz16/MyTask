package com.jxdev.mytask.Infrasture.Contracts;


import com.jxdev.mytask.Models.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface ITaskService {

    TaskDTO createTask(TaskDTO taskDTO);

    TaskDTO updateTask(Long id, TaskDTO taskDTO);

    void deleteTask(Long id);

    Optional<TaskDTO> getTaskById(Long id);

    Optional<TaskDTO> getTaskByTitle(String title);

    List<TaskDTO> getAllTasksByHabit(Long habitId);

    List<TaskDTO> getAllTasks();
}

package com.jxdev.mytask.Infrasture.Services;

import com.jxdev.mytask.Entities.Status;
import com.jxdev.mytask.Entities.Task;
import com.jxdev.mytask.Exceptions.EntityExistingException;
import com.jxdev.mytask.Exceptions.EntityNotFoundException;
import com.jxdev.mytask.Infrasture.Contracts.ITaskService;
import com.jxdev.mytask.Infrasture.Repositories.IStatusRepository;
import com.jxdev.mytask.Infrasture.Repositories.ITaskRepository;
import com.jxdev.mytask.Models.TaskDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServices implements ITaskService {
    @Autowired
    ITaskRepository taskRepository;

    @Autowired
    IStatusRepository statusRepository;

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        try {
            // Validar si el usuario tiene un hábito con el mismo título para evitar duplicados
            /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                String username = authentication.getName();
                Long userId = userRepository.findByUsername(username)
                        .orElseThrow(() -> new EntityNotFoundException("User not found")).getId();
*/
            List<Task> existingTasks = taskRepository.findByHabitId(taskDTO.getHabitId())
                    .stream()
                    .filter(task -> task.getTitle().equalsIgnoreCase(taskDTO.getTitle()))
                    .toList();

            if (!existingTasks.isEmpty()) {
                throw new EntityExistingException("You already have a task with the name " + taskDTO.getTitle());
            }
            ModelMapper mp = new ModelMapper();
            Task task = mp.map(taskDTO, Task.class);
            Task savedTask = taskRepository.save(task);
            ModelMapper mp2 = new ModelMapper();
            return mp2.map(savedTask, TaskDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Error creating task: " + e.getMessage(), e);
        }
    }


    @Override
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        try {
            Optional<Task> existingTask = taskRepository.findById(id);
            if (existingTask.isPresent()) {
                Task task = existingTask.get();
                task.setTitle(taskDTO.getTitle());
                task.setDescription(taskDTO.getDescription());
                task.setGoal(taskDTO.getGoal());
                task.setDueDate(taskDTO.getDueDate());
                task.setReminderTime(taskDTO.getReminderTime());
                task.setPriority(taskDTO.isPriority());
                Status status = statusRepository.findById(taskDTO.getStatusId()).orElseThrow(() -> new EntityNotFoundException("Status not found"));
                task.setStatus(status);

                Task updatedTask = taskRepository.save(task);
                ModelMapper mp = new ModelMapper();
                return mp.map(updatedTask, TaskDTO.class);
            } else {
                throw new EntityExistingException("Task with ID " + id + " not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating task: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteTask(Long id) {
        try {
            if (taskRepository.existsById(id)) {
                taskRepository.deleteById(id);
            } else {
                throw new EntityExistingException("Task with ID " + id + " not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting task: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<TaskDTO> getTaskById(Long id) {
        try {
            Optional<Task> task = taskRepository.findById(id);
            ModelMapper mp = new ModelMapper();
            return task.map(value -> mp.map(value, TaskDTO.class));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving task by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<TaskDTO> getTaskByTitle(String title) {
        try {
            ModelMapper mp = new ModelMapper();
            Optional<Task> existingTask = taskRepository.findByTitle(title);
            if (existingTask.isPresent()) {
                TaskDTO taskDTO = mp.map(existingTask.get(), TaskDTO.class);
                return Optional.of(taskDTO);
            } else {
                throw new EntityExistingException("Task with name: " + title + " does not exist.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving task by title: " + e.getMessage(), e);
        }
    }


    @Override
    public List<TaskDTO> getAllTasksByHabit(Long habitId) {
        try {
            List<Task> tasks = taskRepository.findByHabitId(habitId);

            return tasks.stream()
                    .map(task -> {
                        ModelMapper mp = new ModelMapper();
                        return mp.map(task, TaskDTO.class);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving tasks by habit ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        try {
            List<Task> tasks = taskRepository.findAll();
            return tasks.stream()
                    .map(task -> {
                        ModelMapper mp = new ModelMapper();
                        return mp.map(task, TaskDTO.class);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all tasks: " + e.getMessage(), e);
        }

    }
}

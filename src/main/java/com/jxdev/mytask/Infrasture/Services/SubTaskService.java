package com.jxdev.mytask.Infrasture.Services;

import com.jxdev.mytask.Entities.SubTask;
import com.jxdev.mytask.Entities.Task;
import com.jxdev.mytask.Exceptions.EntityNotFoundException;
import com.jxdev.mytask.Infrasture.Contracts.ISubTaskService;
import com.jxdev.mytask.Infrasture.Repositories.ISubTaskRepository;
import com.jxdev.mytask.Infrasture.Repositories.ITaskRepository;
import com.jxdev.mytask.Models.SubTaskDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubTaskService implements ISubTaskService {
    @Autowired
    ISubTaskRepository subTaskRepository;

    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public SubTaskDTO createSubTask(SubTaskDTO subTaskDTO) {
        try {
            Task task = taskRepository.findById(subTaskDTO.getTaskId())
                    .orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + subTaskDTO.getTaskId()));

            // Crear y guardar la subtask
            SubTask subTask = SubTask.builder()
                    .title(subTaskDTO.getTitle())
                    .isCompleted(subTaskDTO.isCompleted())
                    .task(task)
                    .build();
            ModelMapper modelMapper = new ModelMapper();
            SubTask savedSubTask = subTaskRepository.save(subTask);
            return modelMapper.map(savedSubTask, SubTaskDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Error creating subtask: " + e.getMessage(), e);
        }
    }

    @Override
    public SubTaskDTO updateSubTask(Long id, SubTaskDTO subTaskDTO) {
        try {
            Task task = taskRepository.findById(subTaskDTO.getTaskId())
                    .orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + subTaskDTO.getTaskId()));

            Optional<SubTask> existingSubTask = subTaskRepository.findById(id);
            if (existingSubTask.isPresent()) {
                SubTask subTask = existingSubTask.get();
                subTask.setTitle(subTaskDTO.getTitle());
                subTask.setCompleted(subTaskDTO.isCompleted());
                subTask.setTask(task);

                SubTask updatedSubTask = subTaskRepository.save(subTask);
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(updatedSubTask, SubTaskDTO.class);
            } else {
                throw new EntityNotFoundException("SubTask with ID " + id + " not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating subtask: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteSubTask(Long id) {
        try {
            if (subTaskRepository.existsById(id)) {
                subTaskRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("SubTask with ID " + id + " not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting subtask: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<SubTaskDTO> getSubTaskById(Long id) {
        try {
            Optional<SubTask> subTask = subTaskRepository.findById(id);
            ModelMapper modelMapper = new ModelMapper();
            return Optional.of(modelMapper.map(subTask, SubTaskDTO.class));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving subtask by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<SubTaskDTO> getSubTaskByName(String name) {
        try {
            SubTask subTask = subTaskRepository.findByTitle(name).orElseThrow(()->new EntityNotFoundException("SubTask not found"));
            ModelMapper modelMapper = new ModelMapper();
            return Optional.of(modelMapper.map(subTask, SubTaskDTO.class));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving subtask by name: " + e.getMessage(), e);
        }
    }

    @Override
    public List<SubTaskDTO> getAllSubTasksByTask(Long taskId) {
        try {
            List<SubTask> subTasks = subTaskRepository.findByTaskId(taskId);
            return subTasks.stream()
                    .map(subTask -> {
                        ModelMapper modelMapper = new ModelMapper();
                        return modelMapper.map(subTask, SubTaskDTO.class);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving subtasks by task ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<SubTaskDTO> getAllSubTasks() {
        try {
            List<SubTask> subTasks = subTaskRepository.findAll();
            return subTasks.stream()
                    .map(subTask -> {
                        ModelMapper modelMapper = new ModelMapper();
                        return modelMapper.map(subTask, SubTaskDTO.class);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all subtasks: " + e.getMessage(), e);
        }
    }
}

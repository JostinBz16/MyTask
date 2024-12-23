package com.jxdev.mytask.Infrasture.Services;

import com.jxdev.mytask.Entities.ProgressTask;
import com.jxdev.mytask.Exceptions.EntityExistingException;
import com.jxdev.mytask.Exceptions.EntityNotFoundException;
import com.jxdev.mytask.Infrasture.Contracts.IProgressTaskService;
import com.jxdev.mytask.Infrasture.Repositories.IProgressTaskRepository;
import com.jxdev.mytask.Models.ProgressTaskDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgressTaskService implements IProgressTaskService {
    @Autowired
    IProgressTaskRepository progressTaskRepository;

    @Override
    public ProgressTaskDTO createProgress(ProgressTaskDTO progressDTO) {
        try {
            // Validar si ya existe un ProgressTask con el mismo task y la misma fecha
            Optional<ProgressTask> existingProgressTask = progressTaskRepository.findByTaskIdAndDate(
                    progressDTO.getTaskId(),
                    progressDTO.getProgressDate().toLocalDate() // Asumiendo que ProgressTask tiene un campo "date"
            );

            if (existingProgressTask.isPresent()) {
                throw new EntityExistingException("A progress task already exists for this task on this date.");
            }

            // Convertir ProgressTaskDTO a ProgressTask (entidad)
            ModelMapper modelMapper = new ModelMapper();
            ProgressTask progressTask = modelMapper.map(progressDTO, ProgressTask.class);

            // Guardar la entidad en la base de datos
            ProgressTask savedProgressTask = progressTaskRepository.save(progressTask);

            // Convertir la entidad guardada de nuevo a ProgressTaskDTO y devolver
            return modelMapper.map(savedProgressTask, ProgressTaskDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error creating progress: " + e.getMessage(), e);
        }
    }

    @Override
    public ProgressTaskDTO updateProgress(Long id, ProgressTaskDTO progressDTO) {
        try {
            Optional<ProgressTask> existingProgressOpt = progressTaskRepository.findById(id);
            if (existingProgressOpt.isPresent()) {
                ProgressTask existingProgress = existingProgressOpt.get();
                existingProgress.setCompleted(progressDTO.isCompleted()); // Suponiendo que ProgressTaskDTO tiene esta propiedad
                existingProgress.setDate(progressDTO.getProgressDate().toLocalDate()); // Usamos LocalDate
                // Realiza más cambios según lo que necesites

                // Guardar la entidad actualizada
                ProgressTask updatedProgressTask = progressTaskRepository.save(existingProgress);
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(updatedProgressTask, ProgressTaskDTO.class);
            } else {
                throw new EntityNotFoundException("ProgressTask with ID " + id + " not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating progress: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<ProgressTaskDTO> getProgressById(Long id) {
        try {
            Optional<ProgressTask> progressTask = progressTaskRepository.findById(id);
            return progressTask.map(task -> {
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(task, ProgressTaskDTO.class);
            });
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving progress by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ProgressTaskDTO> getProgressByTask(Long taskId) {
        try {
            List<ProgressTask> progressTasks = progressTaskRepository.findByTaskId(taskId);
            return progressTasks.stream()
                    .map(task -> {
                        ModelMapper modelMapper = new ModelMapper();
                        return modelMapper.map(task, ProgressTaskDTO.class);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving progress by task: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ProgressTaskDTO> getProgressBySubTask(Long subTaskId) {
        // Similar lógica que el anterior, asumiendo que tienes el método en tu repositorio
        return List.of(); // Lógica pendiente
    }

    @Override
    public List<ProgressTaskDTO> getAllProgressByDate(LocalDate date) {
        try {
            List<ProgressTask> progressTasks = progressTaskRepository.findByDate(date);
            return progressTasks.stream()
                    .map(task -> {
                        ModelMapper modelMapper = new ModelMapper();
                        return modelMapper.map(task, ProgressTaskDTO.class);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving progress by date: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteProgress(Long id) {
        try {
            if (progressTaskRepository.existsById(id)) {
                progressTaskRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("ProgressTask with ID " + id + " not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting progress: " + e.getMessage(), e);
        }
    }
}

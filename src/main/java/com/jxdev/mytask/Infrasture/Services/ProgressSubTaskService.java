package com.jxdev.mytask.Infrasture.Services;

import com.jxdev.mytask.Infrasture.Contracts.IProgressSubTaskService;
import com.jxdev.mytask.Infrasture.Repositories.IProgressSubTaskRepository;
import com.jxdev.mytask.Models.ProgressSubTaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProgressSubTaskService implements IProgressSubTaskService {
    @Autowired
    IProgressSubTaskRepository progressSubTaskRepository;

    @Override
    public ProgressSubTaskDTO createProgress(ProgressSubTaskDTO progressDTO) {
        return null;
    }

    @Override
    public ProgressSubTaskDTO updateProgress(Long id, ProgressSubTaskDTO progressDTO) {
        return null;
    }

    @Override
    public Optional<ProgressSubTaskDTO> getProgressById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ProgressSubTaskDTO> getProgressByTask(Long taskId) {
        return List.of();
    }

    @Override
    public List<ProgressSubTaskDTO> getProgressBySubTaskId(Long subTaskId) {
        return List.of();
    }

    @Override
    public List<ProgressSubTaskDTO> getAllProgressByDate(LocalDate date) {
        return List.of();
    }

    @Override
    public void deleteProgress(Long id) {

    }
}

package com.jxdev.mytask.Infrasture.Services;

import com.jxdev.mytask.Entities.Status;
import com.jxdev.mytask.Exceptions.EntityExistingException;
import com.jxdev.mytask.Exceptions.EntityNotFoundException;
import com.jxdev.mytask.Infrasture.Contracts.IStatusService;
import com.jxdev.mytask.Infrasture.Repositories.IStatusRepository;
import com.jxdev.mytask.Models.StatusDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatusService implements IStatusService {
    @Autowired
    IStatusRepository statusRepository;

    @Override
    public StatusDTO createStatus(StatusDTO statusDTO) {
        try {
            // Convertir StatusDTO a entidad Status
            Optional<StatusDTO> statusPresent = getStatusByName(statusDTO.getName());
            if(statusPresent.isPresent()){
                throw new EntityExistingException("Status already have exist with name "+statusPresent.get().getName());
            }
            Status newStatus = Status.builder().name(statusDTO.getName()).build();
            Status savedStatus = statusRepository.save(newStatus);
            // Convertir la entidad guardada de nuevo a DTO y devolverla
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(savedStatus, StatusDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error creating status: " + e.getMessage(), e);
        }
    }

    @Override
    public StatusDTO updateStatus(Long id, StatusDTO statusDTO) {
        try {
            Optional<Status> existingStatusOpt = statusRepository.findById(id);
            if (existingStatusOpt.isPresent()) {
                Status existingStatus = existingStatusOpt.get();
                existingStatus.setName(statusDTO.getName());

                Status updatedStatus = statusRepository.save(existingStatus);
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(updatedStatus, StatusDTO.class);
            } else {
                throw new EntityNotFoundException("Status with ID " + id + " not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating status: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteStatus(Long id) {
        try {
            if (statusRepository.existsById(id)) {
                statusRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("Status with ID " + id + " not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting status: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<StatusDTO> getStatusById(Long id) {
        try {
            Optional<Status> status = statusRepository.findById(id);
            return status.map(value -> {
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(value, StatusDTO.class);
            });
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving status by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<StatusDTO> getStatusByName(String name) {
        try {
            Optional<Status> status = statusRepository.findByName(name);
            return status.map(value -> {
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(value, StatusDTO.class);
            });
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving status by name: " + e.getMessage(), e);
        }
    }

    @Override
    public List<StatusDTO> getAllStatuses() {
        try {
            List<Status> statuses = statusRepository.findAll();
            return statuses.stream()
                    .map(status -> {
                        ModelMapper modelMapper = new ModelMapper();
                        return modelMapper.map(status, StatusDTO.class);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all statuses: " + e.getMessage(), e);

        }
    }
}
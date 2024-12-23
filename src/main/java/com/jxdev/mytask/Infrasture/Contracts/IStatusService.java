package com.jxdev.mytask.Infrasture.Contracts;

import com.jxdev.mytask.Models.StatusDTO;

import java.util.List;
import java.util.Optional;

public interface IStatusService {
    StatusDTO createStatus(StatusDTO statusDTO);

    StatusDTO updateStatus(Long id, StatusDTO statusDTO);

    void deleteStatus(Long id);

    Optional<StatusDTO> getStatusById(Long id);

    Optional<StatusDTO> getStatusByName(String name);

    List<StatusDTO> getAllStatuses();
}

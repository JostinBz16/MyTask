package com.jxdev.mytask.Infrasture.Contracts;

import com.jxdev.mytask.Models.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    Optional<UserDTO> getUserById(Long id);

    List<UserDTO> getAllUsers();
}

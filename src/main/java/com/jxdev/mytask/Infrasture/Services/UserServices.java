package com.jxdev.mytask.Infrasture.Services;

import com.jxdev.mytask.Entities.User;
import com.jxdev.mytask.Exceptions.EntityExistingException;
import com.jxdev.mytask.Exceptions.EntityNotFoundException;
import com.jxdev.mytask.Infrasture.Contracts.IUserService;
import com.jxdev.mytask.Infrasture.Repositories.IUserRepository;
import com.jxdev.mytask.Models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServices implements IUserService {
    @Autowired
    IUserRepository userRepository;


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        try {
            Optional<User> existUser = userRepository.findByEmail(userDTO.getEmail());
            if (existUser.isPresent()) {
                throw new IllegalArgumentException("A user with this email already exists.");
            }

            User user = User.builder()
                    .name(userDTO.getName())
                    .lastName(userDTO.getLastName())
                    .username(userDTO.getUsername())
                    .email(userDTO.getEmail())
                    .password(userDTO.getPassword())
                    .build();

            User savedUser = userRepository.save(user);

            return UserDTO.builder()
                    .id(savedUser.getId())
                    .name(savedUser.getName())
                    .lastName(savedUser.getLastName())
                    .username(savedUser.getUsername())
                    .email(savedUser.getEmail())
                    .build();

        } catch (IllegalArgumentException e) {
            throw new EntityExistingException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while creating the user: " + e.getMessage());
        }
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        try {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));

            existingUser.setName(userDTO.getName());
            existingUser.setLastName(userDTO.getLastName());
            existingUser.setUsername(userDTO.getUsername());
            existingUser.setEmail(userDTO.getEmail());

            User updatedUser = userRepository.save(existingUser);

            return UserDTO.builder()
                    .id(updatedUser.getId())
                    .name(updatedUser.getName())
                    .lastName(updatedUser.getLastName())
                    .username(updatedUser.getUsername())
                    .email(updatedUser.getEmail())
                    .build();

        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the user: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            if (!userRepository.existsById(id)) {
                throw new EntityNotFoundException("User not found with ID: " + id);
            }
            userRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the user: " + e.getMessage());
        }
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        try {
            return userRepository.findById(id).map(user -> UserDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .lastName(user.getLastName())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .build());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving the user: " + e.getMessage());
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        try {
            return userRepository.findAll().stream()
                    .map(user -> UserDTO.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .lastName(user.getLastName())
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .build())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving users: " + e.getMessage());
        }
    }
}

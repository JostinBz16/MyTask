package com.jxdev.mytask.Infrasture.Services;

import com.jxdev.mytask.Entities.Habit;
import com.jxdev.mytask.Entities.User;
import com.jxdev.mytask.Infrasture.Contracts.IHabitService;
import com.jxdev.mytask.Infrasture.Repositories.IHabitRepository;
import com.jxdev.mytask.Infrasture.Repositories.IUserRepository;
import com.jxdev.mytask.Models.HabitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HabitService implements IHabitService {
    @Autowired
    IHabitRepository habitRepository;

    @Autowired
    IUserRepository userRepository;

    @Override
    public HabitDTO createHabit(HabitDTO habitDTO) {
        try {
            User user = userRepository.findById(habitDTO.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + habitDTO.getUserId()));

            Habit habit = Habit.builder()
                    .name(habitDTO.getName())
                    .purpose(habitDTO.getPurpose())
                    .frequency(habitDTO.getFrequency())
                    .user(user)
                    .build();

            Habit savedHabit = habitRepository.save(habit);

            return HabitDTO.builder()
                    .id(savedHabit.getId())
                    .name(savedHabit.getName())
                    .purpose(savedHabit.getPurpose())
                    .frequency(savedHabit.getFrequency())
                    .userId(savedHabit.getUser().getId())
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Error creating habit: " + e.getMessage(), e);
        }
    }

    @Override
    public HabitDTO updateHabit(Long id, HabitDTO habitDTO) {
        try {
            Optional<Habit> habitOpt = habitRepository.findById(id);

            if (habitOpt.isPresent()) {
                Habit habit = habitOpt.get();
                habit.setName(habitDTO.getName());
                habit.setPurpose(habitDTO.getPurpose());
                habit.setFrequency(habitDTO.getFrequency());
                habit = habitRepository.save(habit);

                return new HabitDTO(habit.getId(), habit.getName(), habit.getPurpose(), habit.getFrequency(), habit.getUser().getId());
            }

            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error updating habit: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteHabit(Long id) {
        try {
            if (habitRepository.existsById(id)) {
                habitRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("Habit with ID " + id + " does not exist.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting habit: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<HabitDTO> getHabitById(Long id) {
        try {
            Optional<Habit> habitOpt = habitRepository.findById(id);

            if (habitOpt.isPresent()) {
                Habit habit = habitOpt.get();
                HabitDTO habitDTO = new HabitDTO(habit.getId(), habit.getName(), habit.getPurpose(), habit.getFrequency(), habit.getUser().getId());
                return Optional.of(habitDTO);
            }

            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving habit by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<HabitDTO> getAllHabitsByUser(Long userId) {
        try {
            List<Habit> habits = habitRepository.findByUserId(userId);

            return habits.stream()
                    .map(habit -> new HabitDTO(habit.getId(), habit.getName(), habit.getPurpose(), habit.getFrequency(), habit.getUser().getId()))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Error retrieving habits by user ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<HabitDTO> getAllHabits() {
        try {
            List<Habit> habits = habitRepository.findAll();

            return habits.stream()
                    .map(habit -> new HabitDTO(habit.getId(), habit.getName(), habit.getPurpose(), habit.getFrequency(), habit.getUser().getId()))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all habits: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<HabitDTO> getHabitByName(String name) {
        try {
            Optional<Habit> habitOpt = habitRepository.findByName(name);

            if (habitOpt.isPresent()) {
                Habit habit = habitOpt.get();
                HabitDTO habitDTO = new HabitDTO(habit.getId(), habit.getName(), habit.getPurpose(), habit.getFrequency(), habit.getUser().getId());
                return Optional.of(habitDTO);
            }

            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving habit by name: " + e.getMessage(), e);
        }
    }
}

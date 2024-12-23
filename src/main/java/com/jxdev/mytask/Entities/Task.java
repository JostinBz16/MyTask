package com.jxdev.mytask.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;
        private String description;
        private String goal;
        private LocalDate dueDate; // Fecha límite para la tarea
        private LocalTime reminderTime; // Hora diaria de recordatorio

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "status_id")
        private Status status; // Estado de la tarea (PENDING, COMPLETED, IN_PROGRESS)

        private boolean priority; // Prioridad de la tarea (HIGH, MEDIUM, LOW)

        @ManyToOne
        @JoinColumn(name = "habit_id")
        private Habit habit;

        @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<SubTask> subtasks = new HashSet<>();
}

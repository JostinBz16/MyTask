package com.jxdev.mytask.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private String goal;
    private LocalDate dueDate; // Fecha límite para la tarea
    private LocalTime reminderTime; // Hora diaria de recordatorio
    private boolean priority; // Prioridad de la tarea (HIGH, MEDIUM, LOW)
    private Long statusId; // Estado de la tarea (PENDING, COMPLETED, IN_PROGRESS)
    private Long habitId;
}

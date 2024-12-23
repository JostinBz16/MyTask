package com.jxdev.mytask.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "taskprogress")
public class ProgressTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date; // Fecha del progreso realizado en la tarea
    private boolean completed; // Indica si la tarea fue completada en la fecha especificada

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

}

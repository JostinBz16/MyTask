package com.jxdev.mytask.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subtaskprogress")
public class ProgressSubTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date; // Fecha del progreso en la subtarea
    private boolean completed; // Indica si la subtarea fue completada en esa fecha

    @ManyToOne
    @JoinColumn(name = "subtask_id")
    private SubTask subtask;
}

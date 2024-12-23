package com.jxdev.mytask.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgressSubTaskDTO {
    private Long id;
    private String progressDescription; // Descripción del progreso
    private LocalDateTime progressDate;  // Fecha y hora del progreso
    private Long subTaskId;
}

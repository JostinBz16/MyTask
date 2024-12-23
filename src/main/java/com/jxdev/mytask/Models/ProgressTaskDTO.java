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
public class ProgressTaskDTO {
    private Long id;
    private LocalDateTime progressDate;  // Fecha y hora del progreso
    private boolean completed;
    private Long taskId;
}

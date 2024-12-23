package com.jxdev.mytask.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubTaskDTO {
    private Long id;
    private String title;
    private boolean completed;
    private Long taskId;
}

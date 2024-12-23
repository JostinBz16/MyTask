package com.jxdev.mytask.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HabitDTO {
    private Long id;
    private String name;
    private String purpose; // Purpose of the habit, e.g., "Become a better reader"
    private String frequency;
    private Long userId;
}

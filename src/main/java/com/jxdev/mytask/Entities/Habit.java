package com.jxdev.mytask.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "habits")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String purpose; // Purpose of the habit, e.g., "Become a better reader"
    private String frequency; // Frequency of completion, e.g., DAILY, WEEKLY

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks = new HashSet<>();
}

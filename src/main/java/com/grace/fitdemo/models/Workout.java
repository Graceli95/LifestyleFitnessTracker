package com.grace.fitdemo.models;

import jakarta.persistence. *;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "workouts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name= "workout_id")
    private Integer workoutId;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id")  // Null for global workouts, when none is provided, default value is null
    private User createdBy;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true) //Fetch workout-related schedules: workout.getSchedules();  // Returns all schedules linked to this workout
    private List<Schedule> schedules = new ArrayList<>();

    @Column(nullable = false,length = 100)
    private String workoutType;

    private String description;

    private Integer duration;  //in minutes

    private Double caloriesBurned;

    private boolean isCustom;  // true = user-created, false = global, default is false when no value is provided

    @Column(length = 255)
    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Workout workout = (Workout) o;
        return Objects.equals(workoutId, workout.workoutId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(workoutId);
    }
}

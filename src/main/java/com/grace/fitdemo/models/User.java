package com.grace.fitdemo.models;

import jakarta.persistence. *;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    // ✅ Users can save global meals or their own custom meals
    @ManyToMany
    @JoinTable(
            name="user_saved_meals",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="meal_id")
    )
    private List<Meal> savedMeals = new ArrayList<>();

    // ✅ Users can save global workouts or their own custom workouts
    @ManyToMany
    @JoinTable(
            name="user_saved_workouts",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="workout_id")
    )
    private List<Workout> savedWorkouts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) //orphanRemoval = true: Removes schedules when they’re no longer associated with the user. mappedBy = "user" refers to the user field in Schedule. cascade = CascadeType.ALL: Changes to User will propagate to related schedules.
    private List<Schedule> schedules = new ArrayList<>();

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Meal> meals = new ArrayList<>();

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Workout> workouts = new ArrayList<>();


    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column(nullable = false, length = 255)
    private String passwordHash;

    @CreationTimestamp   // Automatically sets timestamp when entity is created
    private LocalDateTime createdAt;  //Uses Java 8+ date/time API, which is recommended over Date.
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }
}

/*
✅ Global Meals & Workouts:
	•	createdBy = null, isCustom = false → Globally provided.
	•	Users can view and save these without altering the original meal/workout.

✅ User-Created Meals & Workouts:
	•	createdBy = user, isCustom = true → User-owned entry.
	•	Deleting the user can cascade delete their created meals/workouts (if desired).

✅ Saved Meals & Workouts:
	•	Users save meals/workouts through a ManyToMany relationship.
	•	Removing a meal from savedMeals doesn’t delete it from the Meal table.
 */

/*
✅ Use ManyToMany for saved meals and workouts.
✅ Use ManyToOne (with createdBy) to track the creator of custom meals/workouts.
✅ Avoid orphanRemoval in savedMeals and savedWorkouts to prevent accidental deletions.
✅ Use cascade = CascadeType.ALL for user-created meals/workouts (if you want them deleted when the user is deleted).
 */

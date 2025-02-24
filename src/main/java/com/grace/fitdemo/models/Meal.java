package com.grace.fitdemo.models;

import com.grace.fitdemo.enums.MealType;
import jakarta.persistence. *;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "meals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mealId;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id") // Null for global meals
    private User createdBy;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, orphanRemoval = true) //Fetch meal-related schedules: meal.getSchedules();  // Returns all schedules linked to this meal
    private List<Schedule> schedules = new ArrayList<>();

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MealType mealType;

    private String description;

    private Double calories;

    private Integer protein;

    private Integer carbs;

    private Integer fat;

    private boolean isCustom;  // true = user-created, false = global

    @Column(length = 255)
    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(mealId, meal.mealId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mealId);
    }
}

/*
In Meal
@OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Schedule> schedules = new ArrayList<>();

In Workout
@OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Schedule> schedules = new ArrayList<>();

✅ User can indirectly access meal and workout schedules separately through the Meal and Workout entities.
✅ Clear Separation: Users can fetch meal schedules and workout schedules independently.
✅ Flexible Data Access: Only fetch the data you need—no unnecessary data loading.
✅ Improves Readability: Code becomes more intuitive with direct access methods.
 */

/*
🤔 How does this benefit the user?
	•	Scenario 1: User wants to see all their meal schedules:
	•	Fetch the user’s saved meals: user.getSavedMeals()
	•	For each meal, call meal.getSchedules() to get related schedules.

	•	Scenario 2: User wants to see all their workout schedules:
	•	Fetch the user’s saved workouts: user.getSavedWorkouts()
	•	For each workout, call workout.getSchedules() to get related schedules.
 */


/*
🍽️ Why do we need mealType in the Meal model?
✅ Categorizes meals (breakfast, lunch, dinner, snack).
✅ Helps with categorize, filtering and displaying meals in the frontend.
✅ Allows scheduling specific meal types for users.
✅ Enhances overall data consistency and usability.
 */

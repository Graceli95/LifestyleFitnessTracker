package com.grace.fitdemo.models;


import com.grace.fitdemo.enums.ActivityType;
import com.grace.fitdemo.enums.Status;
import jakarta.persistence. *;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name= "schedule_id")
    private Integer scheduleId;


    @ManyToOne //Many schedules can belong to one user. Each schedule must be associated with exactly one user.
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActivityType activityType;

    @ManyToOne
    @JoinColumn(name = "meal_id") // Nullable if activityType is not MEAL
    private Meal meal;

    @ManyToOne
    @JoinColumn(name = "workout_id") // Nullable if activityType is not WORKOUT
    private Workout workout;
//
//    @Column(nullable = false)
//    private Integer activityId; // Refers to either a Meal or Workout ID based on activityType

    @Column(nullable = false)
    private LocalDateTime date;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(scheduleId, schedule.scheduleId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(scheduleId);
    }

    //    public enum ActivityType {
//        MEAL,WORKOUT
//    }
//
//
//    public enum Status {
//        COMPLETED, UPCOMING,SKIPPED
//    }
//
}

/*
	1.	Why the change?
	•	Previously, Schedule referenced activityId and activityType, which could cause ambiguity.
	•	Now, Schedule has direct @ManyToOne relationships with Meal and Workout entities.
	2.	Benefits:
✅ Cleaner, type-safe code: schedule.getMeal() and schedule.getWorkout() are straightforward.
✅ Prevents mismatches (no invalid activityId references).
✅ Works seamlessly with both global and custom entries.
	3.	Behavior:
	•	If activityType = MEAL, the meal field will be set; workout will be null.
	•	If activityType = WORKOUT, the workout field will be set; meal will be null.
 */

/*
@ManyToOne Relationship: Schedule → Meal

🔔 Meaning:
	•	Many schedules can reference the same meal.
	•	This supports scenarios like multiple users scheduling the same global meal.

🧩 Why @ManyToOne?
	•	From the Schedule perspective:
	“Each schedule can involve one meal.”
	•	This allows the Schedule to reference a pre-defined or user-created meal.

🔗 Example:
	•	Meal A (Chicken Salad) is scheduled by multiple users.
	•	Schedules reference Meal A through the meal field.
schedule.setMeal(meal); // Associates schedule with a meal
 */

/*
@ManyToOne Relationship: Schedule → Workout

🔔 Meaning:
	•	Many schedules can reference the same workout.
	•	Ideal for both global and user-created workouts being reused.

🧩 Why @ManyToOne?
	•	From the Schedule perspective:
	“Each schedule can involve one workout.”
	•	This field is used when activityType is WORKOUT.

🔗 Example:
	•	Workout A (Morning Run) is scheduled by many users.
	•	Each Schedule that involves a workout references Workout A.
schedule.setWorkout(workout); // Associates schedule with a workout
 */

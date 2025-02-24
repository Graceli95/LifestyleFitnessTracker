# Per Scholas Java Full Stack Bootcamp Capstone Project: LifestyleFitnessTracker
A comprehensive platform for tracking diet, workouts, and overall fitness journey while fostering social connections.

<img width="1001" alt="fitdemoProjectERD" src="https://github.com/user-attachments/assets/dee2c7a5-b61f-4702-9a22-23b78de276ba" />



Here's the text-based representation of the relationships for the ER Diagram:
```
User (user_id)
    ├───< Meal (meal_id, user_id)
    ├───< Workout (workout_id, user_id)
    └───< Schedule (schedule_id, user_id, activity_type, activity_id, status, date)
                   ├── Links to Meal (meal_id) if activity_type = 'meal'
                   └── Links to Workout (workout_id) if activity_type = 'workout'

src/main/java/com/grace/project/
├── models/         # JPA entities (User, Meal, Workout, Schedule)
│   ├── User.java
│   ├── Meal.java
│   ├── Workout.java
│   └── Schedule.java
├── enums/          # Place all enums here ✅
│   ├── MealType.java
│   ├── ActivityType.java
│   └── Status.java
└── services/       # Business logic
└── controllers/    # REST APIs
```



## Features:
* Schedule meals, workouts  and Log meals/snacks/workouts and track calorie intake.
* Users are able to add their own meals and workouts
* Monitor workout activities with detailed tracking and charts.
* Show  the user’s progress over time and calculate user's workout hours
* Schedule personalized weekly/monthly plans (workouts, meals).

### Deferred Features
* Notifications for reminders (e.g., too many cheat days)
* Social connections for sharing recipes, goals, and updates.

## Core Functionalities (MVP Functionalities):
* User Authentication
  * Users must be able to register, log in, and manage their sessions securely.
* Meal menu
  * Show users what meals they can choose from, and Logging meals, add to their schedule, Users are able to add their own meals
* Workout selection
  * Show users workouts selection, log workouts, can add to their schedule, Users are able to add their own meals and workouts

* Scheduling
Adding and viewing planned meals or workouts, tracking meal history & tracking workout history . upcoming and completed
* Data Visualization
Basic progress charts (e.g., calories consumed vs. burned, workout types).

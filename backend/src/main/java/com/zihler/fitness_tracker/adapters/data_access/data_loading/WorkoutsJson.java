package com.zihler.fitness_tracker.adapters.data_access.data_loading;

import java.util.List;

public class WorkoutsJson {
    private List<WorkoutJson> workouts;

    public WorkoutsJson() {
    }

    public List<WorkoutJson> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<WorkoutJson> workouts) {
        this.workouts = workouts;
    }

    @Override
    public String toString() {
        return "WorkoutsJson{" +
                "workouts=" + workouts +
                '}';
    }
}

package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.domain.values.Workouts;

import java.util.List;
import java.util.stream.Collectors;

public class WorkoutsDocument {
    private List<WorkoutDocument> workouts;

    public WorkoutsDocument(List<WorkoutDocument> workouts) {
        this.workouts = workouts;
    }

    public List<WorkoutDocument> getWorkouts() {
        return workouts;
    }
}

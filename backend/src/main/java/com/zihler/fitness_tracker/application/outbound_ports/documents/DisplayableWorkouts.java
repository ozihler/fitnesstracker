package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.domain.values.Workouts;

import java.util.List;
import java.util.stream.Collectors;

public class DisplayableWorkouts {
    private List<WorkoutDocument> workouts;

    public DisplayableWorkouts(List<WorkoutDocument> workouts) {
        this.workouts = workouts;
    }

    public static DisplayableWorkouts of(Workouts workouts) {
        return new DisplayableWorkouts(
                workouts.getWorkouts().stream().map(WorkoutDocument::of).collect(Collectors.toList())
        );
    }

    public List<WorkoutDocument> getWorkouts() {
        return workouts;
    }
}

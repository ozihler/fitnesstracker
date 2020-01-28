package com.zihler.fitness_tracker.application.use_cases.update_workouts;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.domain.entities.Workout;

public class DifferingCreationTimeException extends RuntimeException {
    public DifferingCreationTimeException(Workout old, WorkoutDocument update) {
        super(String.format("Creation times were supposed to be the same but were different: old: %s, update: %s", old.getCreationDateTime(), update.getCreationDateTime()));
    }
}

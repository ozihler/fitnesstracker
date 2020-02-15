package com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.workouts.inputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.workouts.WorkoutFile;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.Workouts;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class WorkoutFilesInput {
    private Set<WorkoutFile> files;

    public WorkoutFilesInput(Set<WorkoutFile> files) {
        this.files = files;
    }

    public Workouts workouts() {
        List<Workout> workouts = files
                .stream()
                .filter(Objects::nonNull)
                .map(WorkoutFileInput::new)
                .map(WorkoutFileInput::workout)
                .collect(toList());

        return Workouts.of(workouts);
    }


}


package com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.workouts.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.FullWorkoutViewModel;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.Workouts;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class WorkoutFilesInput {
    private Set<FullWorkoutViewModel> files;

    public WorkoutFilesInput(Set<FullWorkoutViewModel> files) {
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


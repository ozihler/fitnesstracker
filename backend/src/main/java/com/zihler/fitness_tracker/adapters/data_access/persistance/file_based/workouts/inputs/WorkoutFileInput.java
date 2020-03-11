package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.workouts.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.FullMuscleGroupViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.FullWorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.FullSetViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WaitingTime;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.*;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class WorkoutFileInput {
    private FullWorkoutViewModel workoutFile;

    WorkoutFileInput(FullWorkoutViewModel workoutFile) {
        this.workoutFile = workoutFile;
    }

    public Workout workout() {
        return   Workout.from(
                WorkoutId.of(workoutFile.getGid()),
                CreationDate.from(LocalDate.from(Instant.ofEpochMilli(workoutFile.getCreationDate()).atZone(Clock.systemDefaultZone().getZone()))), WorkoutTitle.of(workoutFile.getTitle()),
                toMuscleGroups(workoutFile)
        );
    }

    private MuscleGroups toMuscleGroups(FullWorkoutViewModel w) {
        java.util.List<MuscleGroup> muscleGroups = w.getMuscleGroups()
                .stream()
                .map(this::toMuscleGroup)
                .collect(Collectors.toList());

        return MuscleGroups.of(muscleGroups);
    }

    private MuscleGroup toMuscleGroup(FullMuscleGroupViewModel m) {
        return MuscleGroup.of(m.getName(), Exercises.of(
                m.getExercises().stream().map(e -> Exercise.of(e.getName(), toSetsViewModel(e.getSets()))).collect(Collectors.toList())));
    }

    private Sets toSetsViewModel(List<FullSetViewModel> sets) {
        return Sets.of(sets.stream()
                .map(s -> Set.withValues(Weight.of(s.getWeight(), UnitOfMeasurement.fromShortName(s.getWeightUnit())), Repetitions.of(s.getNumberOfRepetitions()), WaitingTime.of(s.getWaitingTime(), UnitOfTime.fromShortName(s.getWaitingTimeUnit()))))
                .collect(Collectors.toList()));
    }
}

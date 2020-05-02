package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.workouts.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Sets;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

public class WorkoutFilesOutput {
    public Workout toStore;

    private WorkoutFilesOutput(Workout toStore) {
        this.toStore = toStore;
    }

    public static WorkoutFilesOutput from(Workout toStore) {
        return new WorkoutFilesOutput(toStore);
    }

    public WorkoutViewModel asFile() {
        return WorkoutViewModel.of(
                this.toStore.getWorkoutId().toString(),
                this.toStore.getWorkoutTitle().toString(),
                toStore.getCreationDate().toMillis(),
                toMuscleGroupFiles(this.toStore)
        );
    }

    private List<MuscleGroupViewModel> toMuscleGroupFiles(Workout workout) {
        return workout.getMuscleGroups().getMuscleGroups().stream()
                .map(m -> new MuscleGroupViewModel(asString(m), asStrings(m.getExercises()))).collect(toUnmodifiableList());
    }

    private String asString(MuscleGroup muscleGroup) {
        return muscleGroup.getName().toString();
    }

    private List<ExerciseViewModel> asStrings(Exercises exercises) {
        return exercises.getExercises().stream().map(e -> new ExerciseViewModel(e.getName().toString(), toViewModel(e.getSets()))).collect(toList());
    }

    private List<SetViewModel> toViewModel(Sets sets) {
        return sets.getSets().stream()
                .map(s -> new SetViewModel(
                        s.getWeight().value(),
                        s.getWeight().unitOfMeasurement().shortname(),
                        s.getRepetitions().number(),
                        s.getWaitingTime().value(),
                        s.getWaitingTime().unitOfTime().shortname()))
                .collect(toList());
    }
}

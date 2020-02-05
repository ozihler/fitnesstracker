package com.zihler.fitness_tracker.application.use_cases.update_workout;

import com.zihler.fitness_tracker.application.outbound_ports.documents.ExerciseDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchExercise;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.update_workout.inbound_port.UpdateWorkout;
import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.Sets;

import java.time.ZonedDateTime;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class UpdateWorkoutUseCase implements UpdateWorkout {
    private FetchMuscleGroup fetchMuscleGroup;
    private FetchExercise fetchExercise;
    private FetchWorkout fetchWorkout;
    private StoreWorkout storeWorkouts;

    public UpdateWorkoutUseCase(FetchWorkout fetchWorkout, StoreWorkout storeWorkout, FetchMuscleGroup fetchMuscleGroup, FetchExercise fetchExercise) {
        this.fetchWorkout = fetchWorkout;
        storeWorkouts = storeWorkout;
        this.fetchMuscleGroup = fetchMuscleGroup;
        this.fetchExercise = fetchExercise;
    }

    @Override
    public void invokeWith(WorkoutDocument update, WorkoutPresenter output) {
        Workout old = fetchWorkout.by(update.getWorkoutId());

        if (creationTimesDiffer(old, update)) {
            throw new DifferingCreationTimeException(old, update);
        }

        old.clear();

        MuscleGroups workoutMuscleGroups = new MuscleGroups();
        for (MuscleGroupDocument m : update.getMuscleGroups().getMuscleGroups()) {
            MuscleGroup muscleGroup = fetchMuscleGroup.by(m.getName());
            for (ExerciseDocument e : m.getExercises().getExercises()) {
                Exercise exercise = fetchExercise.named(e.getName());
                Sets sets = Sets.of(e.getSets().getSets().stream().map(s -> new com.zihler.fitness_tracker.domain.entities.Set(s.getWeight(), s.getRepetitions(), s.getWaitingTime())).collect(toList()));
                exercise.addAll(sets);
                muscleGroup.add(exercise);
            }
            workoutMuscleGroups.add(muscleGroup);
        }

        Workout updatedWorkout = Workout.update(old.getWorkoutId(), old.getCreationDateTime(), update.getWorkoutTitle(), workoutMuscleGroups);

        Workout storedUpdatedWorkout = storeWorkouts.as(updatedWorkout);

        output.present(WorkoutDocument.of(storedUpdatedWorkout));
    }

    private Set<Name> toNames(MuscleGroupsDocument muscleGroups) {
        return muscleGroups.getMuscleGroups()
                .stream()
                .map(MuscleGroupDocument::getName)
                .collect(toSet());
    }

    private boolean creationTimesDiffer(Workout old, WorkoutDocument update) {
        return asMillis(old, old.getCreationDateTime()) != asMillis(old, update.getCreationDateTime());
    }

    private long asMillis(Workout old, ZonedDateTime creationDateTime) {
        return old.getCreationDateTime().toInstant().toEpochMilli();
    }


}

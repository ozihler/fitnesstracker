package com.zihler.fitness_tracker.application.use_cases.update_workout;

import com.zihler.fitness_tracker.application.outbound_ports.documents.ExerciseDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.update_workout.inbound_port.UpdateWorkout;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.Exercise;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.Sets;

import java.time.ZonedDateTime;

import static java.util.stream.Collectors.toList;

public class UpdateWorkoutUseCase implements UpdateWorkout {
    private FetchWorkout fetchWorkout;
    private StoreWorkout storeWorkouts;

    public UpdateWorkoutUseCase(FetchWorkout fetchWorkout, StoreWorkout storeWorkout) {
        this.fetchWorkout = fetchWorkout;
        storeWorkouts = storeWorkout;
    }

    @Override
    public void invokeWith(WorkoutDocument update, WorkoutPresenter output) {
        Workout old = fetchWorkout.by(update.getWorkoutId());

        if (creationTimesDiffer(old, update)) {
            throw new DifferingCreationTimeException(old, update);
        }

        old.clear();

        // todo introduce UpdatedWorkout/UpdatedMuscleGroups/UpdatedExercises/UpdatedSets Roles
        MuscleGroups workoutMuscleGroups = new MuscleGroups();
        for (MuscleGroupDocument m : update.getMuscleGroups().getMuscleGroups()) {
            MuscleGroup muscleGroup = new MuscleGroup(m.getName());
            for (ExerciseDocument e : m.getExercises().getExercises()) {
                Exercise exercise = new Exercise(muscleGroup, e.getName());
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

    private boolean creationTimesDiffer(Workout old, WorkoutDocument update) {
        return asMillis(old.getCreationDateTime()) != asMillis(update.getCreationDateTime());
    }

    private long asMillis(ZonedDateTime creationDateTime) {
        return creationDateTime.toInstant().toEpochMilli();
    }


}

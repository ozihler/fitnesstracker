package com.zihler.fitness_tracker.application.use_cases.update_workout.roles;

import com.zihler.fitness_tracker.application.outbound_ports.documents.ExerciseDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.update_workout.DifferingCreationTimeException;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.Exercise;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.Sets;

import java.time.ZonedDateTime;

import static java.util.stream.Collectors.toList;

public class UpdatableWorkout {
    private Workout self;
    private StoreWorkout storeWorkout;
    private WorkoutPresenter output;

    public UpdatableWorkout(Workout self, StoreWorkout storeWorkout, WorkoutPresenter output) {
        this.self = self;
        this.storeWorkout = storeWorkout;
        this.output = output;
    }

    public void updateWith(WorkoutDocument update) {
        if (creationTimesDiffer(self, update)) {
            throw new DifferingCreationTimeException(self, update);
        }

        // todo introduce UpdatedWorkout/UpdatedMuscleGroups/UpdatedExercises/UpdatedSets Roles
        MuscleGroups workoutMuscleGroups = new MuscleGroups();

        for (MuscleGroupDocument m : update.getMuscleGroups().getMuscleGroups()) {
            MuscleGroup muscleGroup = new MuscleGroup(m.getName());
            workoutMuscleGroups.add(muscleGroup);

            for (ExerciseDocument e : m.getExercises().getExercises()) {
                Exercise exercise = new Exercise(muscleGroup, e.getName());
                Sets sets = Sets.of(e.getSets().getSets().stream().map(s -> new com.zihler.fitness_tracker.domain.entities.Set(s.getWeight(), s.getRepetitions(), s.getWaitingTime())).collect(toList()));
                exercise.addAll(sets);

                muscleGroup.add(exercise);
            }
        }

        self = Workout.update(self.getWorkoutId(),
                self.getCreationDateTime(),
                update.getWorkoutTitle(),
                workoutMuscleGroups);
    }

    public void store() {
        self = storeWorkout.as(self);
    }

    public void present() {
        output.present(WorkoutDocument.of(self));
    }

    private static long asMillis(ZonedDateTime creationDateTime) {
        return creationDateTime.toInstant().toEpochMilli();
    }

    private static boolean creationTimesDiffer(Workout old, WorkoutDocument update) {
        return asMillis(old.getCreationDateTime()) != asMillis(update.getCreationDateTime());
    }
}

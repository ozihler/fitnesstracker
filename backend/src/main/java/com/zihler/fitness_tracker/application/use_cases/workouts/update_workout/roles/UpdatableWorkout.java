package com.zihler.fitness_tracker.application.use_cases.workouts.update_workout.roles;

import com.zihler.fitness_tracker.application.outbound_ports.documents.ExerciseDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.*;

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
        // todo introduce UpdatedWorkout/UpdatedMuscleGroups/UpdatedExercises/UpdatedSets Roles
        MuscleGroups workoutMuscleGroups = new MuscleGroups();

        for (MuscleGroupDocument m : update.getMuscleGroups().getMuscleGroups()) {
            MuscleGroup muscleGroup = MuscleGroup.of(m.getName());
            workoutMuscleGroups.add(muscleGroup);

            for (ExerciseDocument e : m.getExercises().getExercises()) {
                Sets sets = Sets.of(e.getSets().getSets().stream().map(s -> new Set(s.getWeight(), s.getRepetitions(), s.getWaitingTime())).collect(toList()));
                Exercise exercise = Exercise.of(e.getName(),sets);
                muscleGroup.add(exercise);
            }
        }

        self = Workout.from(self.getWorkoutId(), update.getCreationDate(), update.getWorkoutTitle(), workoutMuscleGroups);
    }

    public void store() {
        self = storeWorkout.withValues(self);
    }

    public void present() {
        output.present(WorkoutDocument.of(self));
    }

}

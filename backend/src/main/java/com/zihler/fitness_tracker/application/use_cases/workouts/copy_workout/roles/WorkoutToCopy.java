package com.zihler.fitness_tracker.application.use_cases.workouts.copy_workout.roles;

import com.zihler.fitness_tracker.application.outbound_ports.gateways.GenerateWorkoutId;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.CopiedWorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.workouts.copy_workout.roles.exceptions.WorkoutNotCopiedYetException;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

public class WorkoutToCopy {
    private final Workout self;
    private final StoreWorkout storeWorkout;
    private final CopiedWorkoutPresenter output;
    private final GenerateWorkoutId generateWorkoutId;
    private WorkoutId idOfCopiedWorkout;

    public WorkoutToCopy(Workout self, StoreWorkout storeWorkout, GenerateWorkoutId generateWorkoutId, CopiedWorkoutPresenter output) {
        this.self = self;
        this.storeWorkout = storeWorkout;
        this.generateWorkoutId = generateWorkoutId;
        this.output = output;
    }

    public WorkoutToCopy makeCopy() {
        Workout copyOfWorkout = Workout.of(newWorkoutId(), copyOfWorkoutTitle(), copyOfMuscleGroups());
        Workout storedCopyOfWorkout = storeWorkout.withValues(copyOfWorkout);
        idOfCopiedWorkout = storedCopyOfWorkout.getWorkoutId();
        return this;
    }

    private MuscleGroups copyOfMuscleGroups() {
        return new MuscleGroupsToCopy(self.getMuscleGroups()).copy();
    }

    private WorkoutId newWorkoutId() {
        return generateWorkoutId.next();
    }

    private WorkoutTitle copyOfWorkoutTitle() {
        return WorkoutTitle.of(self.getWorkoutTitle().toString());
    }

    public void presentCopiedWorkoutId() {
        if (idOfCopiedWorkout == null) {
            throw new WorkoutNotCopiedYetException("You forgot to copy the workout first");
        }

        output.present(idOfCopiedWorkout);
    }
}

package com.zihler.fitness_tracker.application.use_cases.update_workouts;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.update_workouts.inbound_port.UpdateWorkout;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class UpdateWorkoutUseCase implements UpdateWorkout {
    private FetchMuscleGroup fetchMuscleGroup;
    private FetchWorkout fetchWorkout;
    private StoreWorkout storeWorkouts;

    public UpdateWorkoutUseCase(FetchWorkout fetchWorkout, StoreWorkout storeWorkouts, FetchMuscleGroup fetchMuscleGroup) {
        this.fetchWorkout = fetchWorkout;
        this.storeWorkouts = storeWorkouts;
        this.fetchMuscleGroup = fetchMuscleGroup;
    }

    @Override
    public void callWith(WorkoutDocument update, WorkoutPresenter output) {
        Workout old = fetchWorkout.by(update.getGid());

        if (!old.getCreationDateTime().equals(update.getCreationDateTime())) {
            throw new DifferingCreationTimeException(old, update);
        }

        MuscleGroups updatedMuscleGroups = new MuscleGroups(update.getMuscleGroups()
                .getMuscleGroups()
                .stream()
                .map(m -> fetchMuscleGroup.byName(m.getName()))
                .collect(toSet()));

        Workout updatedWorkout = Workout.updateWorkout(old.getGid(), old.getCreationDateTime(), update.getWorkoutTitle(), updatedMuscleGroups);

        Workout storedUpdatedWorkout = storeWorkouts.as(updatedWorkout);

        output.present(WorkoutDocument.of(storedUpdatedWorkout));
    }


}

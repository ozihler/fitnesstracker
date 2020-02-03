package com.zihler.fitness_tracker.application.use_cases.update_workout;

import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.StoreWorkout;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutPresenter;
import com.zihler.fitness_tracker.application.use_cases.update_workout.inbound_port.UpdateWorkout;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.MuscleGroups;

import java.time.ZonedDateTime;

import static java.util.stream.Collectors.toSet;

public class UpdateWorkoutUseCase implements UpdateWorkout {
    private FetchMuscleGroup fetchMuscleGroup;
    private FetchWorkout fetchWorkout;
    private StoreWorkout storeWorkouts;

    public UpdateWorkoutUseCase(FetchWorkout fetchWorkout, StoreWorkout storeWorkout, FetchMuscleGroup fetchMuscleGroup) {
        this.fetchWorkout = fetchWorkout;
        this.storeWorkouts = storeWorkout;
        this.fetchMuscleGroup = fetchMuscleGroup;
    }

    @Override
    public void callWith(WorkoutDocument update, WorkoutPresenter output) {
        Workout old = fetchWorkout.by(update.getGid());

        if (creationTimesDiffer(old, update)) {
            throw new DifferingCreationTimeException(old, update);
        }

        MuscleGroups workoutMuscleGroups = fetchMuscleGroups(update.getMuscleGroups());

        Workout updatedWorkout = Workout.update(old.getGid(), old.getCreationDateTime(), update.getWorkoutTitle(), workoutMuscleGroups);

        Workout storedUpdatedWorkout = storeWorkouts.as(updatedWorkout);

        output.present(WorkoutDocument.of(storedUpdatedWorkout));
    }

    private MuscleGroups fetchMuscleGroups(MuscleGroupsDocument muscleGroups) {
        return new MuscleGroups(muscleGroups.getMuscleGroups()
                .stream()
                .map(m -> fetchMuscleGroup.byName(m.getName()))
                .collect(toSet()));
    }

    private boolean creationTimesDiffer(Workout old, WorkoutDocument update) {
        return asMillis(old, old.getCreationDateTime()) != asMillis(old, update.getCreationDateTime());
    }

    private long asMillis(Workout old, ZonedDateTime creationDateTime) {
        return old.getCreationDateTime().toInstant().toEpochMilli();
    }


}

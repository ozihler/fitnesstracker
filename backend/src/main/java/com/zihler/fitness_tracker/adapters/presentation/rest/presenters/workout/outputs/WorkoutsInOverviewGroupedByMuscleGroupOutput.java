package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.outputs.MuscleGroupNameOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.viewmodels.WorkoutsInOverviewGroupedByMuscleGroupViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupNameViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutInOverviewViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutsDocument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkoutsInOverviewGroupedByMuscleGroupOutput {
    private WorkoutsDocument workouts;

    public WorkoutsInOverviewGroupedByMuscleGroupOutput(WorkoutsDocument workouts) {
        this.workouts = workouts;
    }

    public WorkoutsInOverviewGroupedByMuscleGroupViewModel workoutsGroupedByMuscleGroup() {

        Map<MuscleGroupNameViewModel, List<WorkoutInOverviewViewModel>> muscleGroupToWorkout = new HashMap<>();

        workouts.getWorkouts()
                .forEach(workout -> {
                    var output = new WorkoutInOverviewOutput(workout);

                    workout.getMuscleGroups()
                            .getMuscleGroups()
                            .stream()
                            .map(MuscleGroupDocument::getName)
                            .map(MuscleGroupNameOutput::new)
                            .map(MuscleGroupNameOutput::toViewModel)
                            .forEach(muscleGroupName -> muscleGroupToWorkout.computeIfAbsent(muscleGroupName, k -> new ArrayList<>()).add(output.toViewModel()));

                });

        return new WorkoutsInOverviewGroupedByMuscleGroupViewModel(muscleGroupToWorkout);
    }
}

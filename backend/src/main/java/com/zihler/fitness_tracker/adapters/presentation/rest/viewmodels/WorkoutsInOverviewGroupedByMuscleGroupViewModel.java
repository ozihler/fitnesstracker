package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkoutsInOverviewGroupedByMuscleGroupViewModel {
    private Map<MuscleGroupViewModel, List<WorkoutViewModel>> muscleGroupToWorkouts;

    public WorkoutsInOverviewGroupedByMuscleGroupViewModel(Map<MuscleGroupViewModel, List<WorkoutViewModel>> muscleGroupToWorkouts) {
        this.muscleGroupToWorkouts = muscleGroupToWorkouts;
    }

    public WorkoutsInOverviewGroupedByMuscleGroupViewModel() {
        this(new HashMap<>());
    }

    public Map<MuscleGroupViewModel, List<WorkoutViewModel>> getMuscleGroupToWorkouts() {
        return muscleGroupToWorkouts;
    }
}

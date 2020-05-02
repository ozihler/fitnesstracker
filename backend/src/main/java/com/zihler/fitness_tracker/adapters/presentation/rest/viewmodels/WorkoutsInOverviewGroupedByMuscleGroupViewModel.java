package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.MuscleGroupNameViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutInOverviewViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkoutsInOverviewGroupedByMuscleGroupViewModel {
    private Map<MuscleGroupNameViewModel, List<WorkoutInOverviewViewModel>> muscleGroupToWorkouts;

    public WorkoutsInOverviewGroupedByMuscleGroupViewModel(Map<MuscleGroupNameViewModel, List<WorkoutInOverviewViewModel>> muscleGroupToWorkouts) {
        this.muscleGroupToWorkouts = muscleGroupToWorkouts;
    }

    public WorkoutsInOverviewGroupedByMuscleGroupViewModel() {
        this(new HashMap<>());
    }

    public Map<MuscleGroupNameViewModel, List<WorkoutInOverviewViewModel>> getMuscleGroupToWorkouts() {
        return muscleGroupToWorkouts;
    }
}

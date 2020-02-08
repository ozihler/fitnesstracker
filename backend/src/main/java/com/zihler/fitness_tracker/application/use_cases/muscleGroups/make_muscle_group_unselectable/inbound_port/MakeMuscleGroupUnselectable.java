package com.zihler.fitness_tracker.application.use_cases.muscleGroups.make_muscle_group_unselectable.inbound_port;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.MuscleGroupPresenter;
import com.zihler.fitness_tracker.domain.values.MuscleGroupName;

public interface MakeMuscleGroupUnselectable {
    void invokeWith(MuscleGroupName muscleGroupName, MuscleGroupPresenter output);
}

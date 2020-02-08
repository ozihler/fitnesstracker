package com.zihler.fitness_tracker.application.use_cases.muscleGroups.view_all_muscle_groups.inbound_port;

import com.zihler.fitness_tracker.application.outbound_ports.presenters.MuscleGroupsPresenter;

public interface ViewAllMuscleGroups {
    void invokeWith(MuscleGroupsPresenter presenter);
}

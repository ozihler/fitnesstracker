package com.zihler.fitness_tracker.application.use_cases.create_muscle_groups.inbound_port;

import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.MuscleGroupsPresenter;

public interface CreateMuscleGroups {
    void callWith(MuscleGroupsDocument muscleGroups, MuscleGroupsPresenter presenter);
}

package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises;

import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchExercises;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExercisesPresenter;
import com.zihler.fitness_tracker.application.use_cases.view_all_exercises_for_muscle_group.ViewAllExercisesForMuscleGroupUseCase;
import com.zihler.fitness_tracker.application.use_cases.view_all_exercises_for_muscle_group.inbound_port.ViewAllExercisesForMuscleGroup;
import com.zihler.fitness_tracker.domain.values.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViewAllExercisesController {
    private ViewAllExercisesForMuscleGroup viewAllExercisesForMuscleGroup;

    @Autowired
    public ViewAllExercisesController(FetchExercises fetchExercises, FetchMuscleGroup fetchMuscleGroup) {
        this.viewAllExercisesForMuscleGroup = new ViewAllExercisesForMuscleGroupUseCase(fetchExercises, fetchMuscleGroup);
    }

    public void fetchAllExercisesForMuscleGroup(String muscleGroupName, ExercisesPresenter output) {
        MuscleGroupDocument muscleGroup = MuscleGroupDocument.of(Name.of(muscleGroupName));

        this.viewAllExercisesForMuscleGroup.invokeWith(muscleGroup, output);
    }
}

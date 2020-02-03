package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.requests.ExercisesToCreate;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.Names;

public class CreateExercisesInput {
    private String muscleGroupName;
    private String exercisesNames;

    public CreateExercisesInput(String muscleGroupName, String exercisesNames) {
        this.muscleGroupName = muscleGroupName;
        this.exercisesNames = exercisesNames;
    }

    public ExercisesToCreate toCreate() {
        Name muscleGroupName = Name.of(this.muscleGroupName);
        Names exercisesToCreate = Names.in(exercisesNames);

        return ExercisesToCreate.of(muscleGroupName, exercisesToCreate);
    }
}

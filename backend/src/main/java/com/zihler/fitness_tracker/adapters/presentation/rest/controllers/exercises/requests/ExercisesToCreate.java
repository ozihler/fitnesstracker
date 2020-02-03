package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.exercises.requests;

import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.Names;

public class ExercisesToCreate {
    private final Name muscleGroupName;
    private final Names exercisesToCreate;

    public ExercisesToCreate(Name muscleGroupName, Names exercisesToCreate) {
        this.muscleGroupName = muscleGroupName;
        this.exercisesToCreate = exercisesToCreate;
    }

    public static ExercisesToCreate of(Name muscleGroupName, Names exercisesToCreate) {
        return new ExercisesToCreate(muscleGroupName, exercisesToCreate);
    }

    public Name parentMuscleGroup() {
        return muscleGroupName;
    }

    public Names exercisesToCreate() {
        return exercisesToCreate;
    }
}

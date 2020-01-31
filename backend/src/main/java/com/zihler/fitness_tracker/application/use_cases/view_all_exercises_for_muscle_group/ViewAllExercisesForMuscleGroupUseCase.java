package com.zihler.fitness_tracker.application.use_cases.view_all_exercises_for_muscle_group;

import com.zihler.fitness_tracker.application.outbound_ports.documents.*;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchExercises;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchMuscleGroup;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExercisesPresenter;
import com.zihler.fitness_tracker.application.use_cases.view_all_exercises_for_muscle_group.inbound_port.ViewAllExercisesForMuscleGroup;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.Sets;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ViewAllExercisesForMuscleGroupUseCase implements ViewAllExercisesForMuscleGroup {

    public ViewAllExercisesForMuscleGroupUseCase(FetchExercises fetchExercises, FetchMuscleGroup fetchMuscleGroup) {
        this.fetchExercises = fetchExercises;
        this.fetchMuscleGroup = fetchMuscleGroup;
    }

    private FetchExercises fetchExercises;
    private FetchMuscleGroup fetchMuscleGroup;

    @Override
    public void invokeWith(MuscleGroupDocument muscleGroup, ExercisesPresenter output) {
        MuscleGroup foundMuscleGroup = fetchMuscleGroup.byName(muscleGroup.getName());
        Exercises exercises = fetchExercises.forMuscleGroup(foundMuscleGroup);
        output.present(toDocument(exercises));
    }

    private ExercisesDocument toDocument(Exercises exercises) {
        return ExercisesDocument.of(toDocuments(exercises));
    }

    private Set<ExerciseDocument> toDocuments(Exercises exercises) {
        return exercises.getExercises().stream().map(exercise -> ExerciseDocument.of(MuscleGroupDocument.of(exercise.getMuscleGroup()), exercise.getName(), toDocument(exercise.getSets()))).collect(Collectors.toSet());
    }

    private SetsDocument toDocument(Sets sets) {
        return SetsDocument.of(sets.getSets().stream().map(set -> SetDocument.of(set.getGid(), set.getWeight(), set.getRepetitions(), set.getWaitingTime())).collect(toList()));
    }

}

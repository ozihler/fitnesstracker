package com.zihler.fitness_tracker.application.use_cases.view_all_exercises_for_muscle_group;

import com.zihler.fitness_tracker.application.outbound_ports.documents.*;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchExercises;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExercisesPresenter;
import com.zihler.fitness_tracker.application.use_cases.view_all_exercises_for_muscle_group.inbound_port.ViewAllExercisesForMuscleGroup;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.MuscleGroupName;
import com.zihler.fitness_tracker.domain.values.Sets;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ViewAllExercisesForMuscleGroupUseCase implements ViewAllExercisesForMuscleGroup {

    private FetchExercises fetchExercises;

    public ViewAllExercisesForMuscleGroupUseCase(FetchExercises fetchExercises) {
        this.fetchExercises = fetchExercises;
    }

    @Override
    public void invokeWith(MuscleGroupName muscleGroupName, ExercisesPresenter output) {
        Exercises exercises = fetchExercises.forMuscleGroup(muscleGroupName);
        Set<ExerciseDocument> documents = toDocuments(exercises);
        output.present(ExercisesDocument.of(documents));
    }

    private Set<ExerciseDocument> toDocuments(Exercises exercises) {
        return exercises.getExercises().stream()
                .map(exercise -> ExerciseDocument.of(
                        MuscleGroupDocument.of(exercise.getMuscleGroup()),
                        exercise.getName(),
                        toDocument(exercise.getSets())))
                .collect(Collectors.toSet());
    }

    private SetsDocument toDocument(Sets sets) {
        List<SetDocument> documents = sets.getSets().stream().map(set -> SetDocument.of(set.getWeight(), set.getRepetitions(), set.getWaitingTime())).collect(toList());
        return SetsDocument.of(documents);
    }

}

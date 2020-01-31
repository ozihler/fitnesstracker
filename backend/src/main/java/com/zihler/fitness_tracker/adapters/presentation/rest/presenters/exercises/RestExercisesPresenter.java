package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.MuscleGroupViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExerciseViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExercisesViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExerciseDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExercisesDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExercisesPresenter;
import org.springframework.http.ResponseEntity;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class RestExercisesPresenter implements ExercisesPresenter {
    private ResponseEntity<ExercisesViewModel> exercises;

    public ResponseEntity<ExercisesViewModel> getResponse() {
        return exercises;
    }

    @Override
    public void present(ExercisesDocument exercisesDocument) {
        this.exercises = ResponseEntity.ok(new ExercisesViewModel(toViewModel(exercisesDocument)));
    }

    private Set<ExerciseViewModel> toViewModel(ExercisesDocument exercisesDocument) {
        return exercisesDocument.getExercises().stream()
                .map(this::toViewModel)
                .collect(toSet());
    }

    private ExerciseViewModel toViewModel(ExerciseDocument e) {

        return new ExerciseViewModel(new MuscleGroupViewModel(e.getName().toString()),
                e.getName().toString(),
                e.getSets().getSets().stream().map(s -> new SetViewModel(s.getGid().toLong(), s.getWeight().value(), s.getWeight().unitOfMeasurement().shortname(), s.getRepetitions().number(), s.getWaitingTime().value(), s.getWaitingTime().unitOfTime().shortname())).collect(Collectors.toList()));
    }
}

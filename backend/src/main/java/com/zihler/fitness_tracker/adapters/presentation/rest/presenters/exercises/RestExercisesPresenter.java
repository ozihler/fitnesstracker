package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.RestPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exercises.outputs.ExercisesOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.ExercisesViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.ExercisesDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.ExercisesPresenter;
import org.springframework.http.ResponseEntity;

public class RestExercisesPresenter
        extends RestPresenter<ExercisesViewModel>
        implements ExercisesPresenter {

    @Override
    public void present(ExercisesDocument exercisesDocument) {
        var output = new ExercisesOutput(exercisesDocument);
        
        this.response = ResponseEntity.ok(output.toViewModel());
    }

}

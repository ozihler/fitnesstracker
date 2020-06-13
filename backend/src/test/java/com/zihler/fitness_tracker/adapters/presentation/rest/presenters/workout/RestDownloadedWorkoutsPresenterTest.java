package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutsViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutsDocument;
import com.zihler.fitness_tracker.domain.values.CreationDate;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestDownloadedWorkoutsPresenterTest {

    @Test
    void testPresenterCreatesCorrectFileName() {
        var presenter = new RestDownloadedWorkoutsPresenter();
        presenter.present(new WorkoutsDocument(List.of()));

        assertEquals(String.format(RestDownloadedWorkoutsPresenter.FILE_NAME_TEMPLATE, CreationDate.now().toString()), presenter.getResponse().getHeaders().getContentDisposition().getFilename());
    }

    @Test
    void testPresenterContainsCorrectValues() throws IOException {
        var presenter = new RestDownloadedWorkoutsPresenter();
        var workoutToPresent = new WorkoutDocument(
                WorkoutId.of("1-1"),
                CreationDate.now(),
                new MuscleGroupsDocument(),
                false
        );
        presenter.present(new WorkoutsDocument(List.of(workoutToPresent)));

        var body = presenter.getResponse().getBody();

        var objectMapper = new ObjectMapper();

        var workoutsViewModel = objectMapper.readValue(body.getInputStream(), WorkoutsViewModel.class);
        var workouts = workoutsViewModel.getWorkouts();
        assertEquals(1, workouts.size());
        assertEquals(workoutToPresent.getWorkoutId().toString(), workouts.get(0).getWorkoutId());
        assertEquals(workoutToPresent.getCreationDate().toMillis(), workouts.get(0).getCreationDate());
        assertEquals(workoutToPresent.getMuscleGroups().getMuscleGroups().size(), workouts.get(0).getMuscleGroups().size());

    }
}

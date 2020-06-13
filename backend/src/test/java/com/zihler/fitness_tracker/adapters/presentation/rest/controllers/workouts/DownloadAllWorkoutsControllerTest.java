package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.workouts.InMemoryWorkoutRepository;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutsViewModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.zihler.fitness_tracker.fixtures.WorkoutFixtures.FULL_BACK_BICEPS_WORKOUT;
import static com.zihler.fitness_tracker.fixtures.WorkoutFixtures.FULL_CHEST_TRICEPS_WORKOUT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DownloadAllWorkoutsControllerTest {

    @Test
    void canDownloadAllWorkouts() throws IOException {
        var repo = new InMemoryWorkoutRepository();
        repo.withValues(FULL_BACK_BICEPS_WORKOUT);
        repo.withValues(FULL_CHEST_TRICEPS_WORKOUT);

        var controller = new DownloadAllWorkoutsController(repo);
        var body = controller.downloadAllWorkouts().getBody();

        var viewModel = new ObjectMapper().readValue(body.getInputStream(), WorkoutsViewModel.class);

        List<WorkoutViewModel> workouts = viewModel.getWorkouts();

        assertEquals(List.of(FULL_BACK_BICEPS_WORKOUT, FULL_CHEST_TRICEPS_WORKOUT).size(), workouts.size());
        //todo add equals for comparing workouts with workoutsViewModel
    }

}

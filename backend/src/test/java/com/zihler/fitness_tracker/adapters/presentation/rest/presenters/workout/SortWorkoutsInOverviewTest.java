package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs.WorkoutsInOverviewOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutsDocument;
import com.zihler.fitness_tracker.domain.values.CreationDate;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortWorkoutsInOverviewTest {
    @Test
    void sortsFromNewestToLowest() {
        var w1 = new WorkoutDocument(WorkoutId.of("1"), CreationDate.of(LocalDate.of(2014, 1, 1)), null, false);
        var w2 = new WorkoutDocument(WorkoutId.of("2"), CreationDate.of(LocalDate.of(2018, 1, 1)), null, false);
        var w3 = new WorkoutDocument(WorkoutId.of("3"), CreationDate.of(LocalDate.of(2020, 1, 1)), null, false);
        var w4 = new WorkoutDocument(WorkoutId.of("4"), CreationDate.of(LocalDate.of(2016, 1, 1)), null, false);

        var output = new WorkoutsInOverviewOutput(new WorkoutsDocument(List.of(w2, w1, w4, w3)));

        List<WorkoutViewModel> workouts = output.workouts().getWorkouts();

        assertEquals("3", workouts.get(0).getWorkoutId());
        assertEquals("2", workouts.get(1).getWorkoutId());
        assertEquals("4", workouts.get(2).getWorkoutId());
        assertEquals("1", workouts.get(3).getWorkoutId());
    }

}

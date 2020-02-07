package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs.WorkoutsInOverviewOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutInOverviewViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutsDocument;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortWorkoutsInOverviewTest {
    @Test
    void sortsFromNewestToLowest() {
        var w1 = new WorkoutDocument(WorkoutId.of("1"), ZonedDateTime.from(Instant.ofEpochMilli(10).atZone(Clock.systemDefaultZone().getZone())), WorkoutTitle.of("1"), null);
        var w2 = new WorkoutDocument(WorkoutId.of("2"), ZonedDateTime.from(Instant.ofEpochMilli(100000).atZone(Clock.systemDefaultZone().getZone())), WorkoutTitle.of("2"), null);
        var w3 = new WorkoutDocument(WorkoutId.of("3"), ZonedDateTime.from(Instant.ofEpochMilli(1000000).atZone(Clock.systemDefaultZone().getZone())), WorkoutTitle.of("3"), null);
        var w4 = new WorkoutDocument(WorkoutId.of("4"), ZonedDateTime.from(Instant.ofEpochMilli(100).atZone(Clock.systemDefaultZone().getZone())), WorkoutTitle.of("4"), null);

        var output = new WorkoutsInOverviewOutput(new WorkoutsDocument(List.of(w2, w1, w4, w3)));

        List<WorkoutInOverviewViewModel> workouts = output.workouts().getWorkouts();

        assertEquals("3", workouts.get(0).getGid());
        assertEquals("2", workouts.get(1).getGid());
        assertEquals("4", workouts.get(2).getGid());
        assertEquals("1", workouts.get(3).getGid());
    }

}
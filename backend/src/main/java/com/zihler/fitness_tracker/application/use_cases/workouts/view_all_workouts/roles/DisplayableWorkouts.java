package com.zihler.fitness_tracker.application.use_cases.workouts.view_all_workouts.roles;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.gateways.FetchWorkouts;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutsPresenter;
import com.zihler.fitness_tracker.domain.values.Workouts;

import java.util.List;
import java.util.stream.Collectors;

public class DisplayableWorkouts {
    private final Workouts self;
    private WorkoutsPresenter output;

    public DisplayableWorkouts(FetchWorkouts fetchWorkouts, WorkoutsPresenter output) {
        self = fetchWorkouts.all();
        this.output = output;
    }

    public void present() {
        output.present(toOutput());
    }

    private WorkoutsDocument toOutput() {
        List<WorkoutDocument> output = self.getWorkouts()
                .stream()
                .map(WorkoutDocument::of)
                .collect(Collectors.toList());

        return new WorkoutsDocument(output);
    }
}

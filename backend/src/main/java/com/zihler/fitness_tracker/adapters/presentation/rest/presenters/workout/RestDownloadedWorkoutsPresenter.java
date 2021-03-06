package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zihler.fitness_tracker.adapters.presentation.rest.exceptions.CouldNotMapWorkoutsToBytesException;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.RestPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs.FullWorkoutOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs.FullWorkoutsOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout.outputs.WorkoutsInOverviewOutput;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutsDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.WorkoutsPresenter;
import com.zihler.fitness_tracker.domain.values.CreationDate;
import com.zihler.fitness_tracker.domain.values.CreationDateTime;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.time.format.DateTimeFormatter;

public class RestDownloadedWorkoutsPresenter
        extends RestPresenter<InputStreamResource>
        implements WorkoutsPresenter {


    public static final String FILE_NAME_TEMPLATE = "allWorkouts_%s.json";

    @Override
    public void present(WorkoutsDocument workouts) {
        try {
            var output = new FullWorkoutsOutput(workouts);
            var mapper = new ObjectMapper();

            byte[] workoutsAsBytes = mapper.writeValueAsBytes(output.workouts());

            this.response = ResponseEntity.ok()
                    .contentLength(workoutsAsBytes.length)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName()))
                    .body(new InputStreamResource(new ByteArrayInputStream(workoutsAsBytes)));

        } catch (Exception e) {
            throw new CouldNotMapWorkoutsToBytesException(e);
        }
    }

    private String fileName() {
        return String.format(FILE_NAME_TEMPLATE, CreationDate.now().toString());
    }
}

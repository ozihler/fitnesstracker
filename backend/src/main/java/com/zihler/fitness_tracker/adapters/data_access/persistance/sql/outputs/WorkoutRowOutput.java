package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading.WorkoutJson;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.WorkoutRow;
import com.zihler.fitness_tracker.domain.values.CreationDate;

public class WorkoutRowOutput {
    private final WorkoutJson workoutJson;

    public WorkoutRowOutput(WorkoutJson workoutJson) {
        this.workoutJson = workoutJson;
    }

    public WorkoutRow getRow() {
        var workoutRow = new WorkoutRow();
        workoutRow.setWorkoutId(workoutJson.getWorkoutId());
        workoutRow.setCreationDate(CreationDate.of(workoutJson.getCreationDate()).toLocalDate());
        workoutRow.setDeleted(false);
        workoutRow.setMuscleGroups(new MuscleGroupsRowOutput(workoutJson.getMuscleGroups()).getRows());
        return workoutRow;
    }
}

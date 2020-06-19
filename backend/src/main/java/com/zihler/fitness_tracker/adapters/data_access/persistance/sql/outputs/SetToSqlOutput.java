package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.ExerciseRow;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.SetRow;
import com.zihler.fitness_tracker.domain.entities.Set;

public class SetToSqlOutput {
    private final Set set;
    private final ExerciseRow exerciseRow;

    public SetToSqlOutput(Set set, ExerciseRow exerciseRow) {
        this.set = set;
        this.exerciseRow = exerciseRow;
    }

    public SetRow getRow() {
        var row = new SetRow();

        row.setWeight(set.getWeight().value());
        row.setWeightUnit(set.getWeight().unitOfMeasurement().shortname());
        row.setRepetitions(set.getRepetitions().number());
        row.setWaitingTime(set.getWaitingTime().value());
        row.setWaitingTimeUnit(set.getWaitingTime().unitOfTime().shortname());
        row.setExercise(exerciseRow);

        return row;
    }
}

package com.zihler.fitness_tracker.adapters.data_access.persistance.sql;

import com.zihler.fitness_tracker.adapters.data_access.exceptions.ExerciseNotFoundException;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.ExerciseRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaExerciseRepository extends JpaRepository<ExerciseRow, Long> {
    Optional<ExerciseRow> findByName(String name);

    default ExerciseRow findByNameOrThrow(String exerciseName) {
        return findByName(exerciseName).orElseThrow(() -> new ExerciseNotFoundException("Could not find exercise with name " + exerciseName));
    }
}

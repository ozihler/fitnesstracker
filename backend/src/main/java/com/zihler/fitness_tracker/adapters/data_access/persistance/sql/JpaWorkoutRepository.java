package com.zihler.fitness_tracker.adapters.data_access.persistance.sql;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.WorkoutRow;
import com.zihler.fitness_tracker.domain.exceptions.WorkoutIdNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaWorkoutRepository extends JpaRepository<WorkoutRow, Long> {
    Optional<WorkoutRow> findByWorkoutId(String workoutId);

    default WorkoutRow findByWorkoutIdOrThrow(String workoutId) {
        return findByWorkoutId(workoutId).orElseThrow(() -> new WorkoutIdNotFoundException("could not find workout with id " + workoutId));
    }
}

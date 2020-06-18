package com.zihler.fitness_tracker.adapters.data_access.persistance.sql;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.WorkoutRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaWorkoutRepository extends JpaRepository<WorkoutRow, Long> {
    Optional<WorkoutRow> findByWorkoutId(String workoutId);

}

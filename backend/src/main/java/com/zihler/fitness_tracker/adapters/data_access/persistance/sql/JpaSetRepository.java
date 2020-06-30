package com.zihler.fitness_tracker.adapters.data_access.persistance.sql;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.SetRow;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.WorkoutRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaSetRepository extends JpaRepository<SetRow, Long> {
    List<SetRow> findAllByWorkout(WorkoutRow row);
}

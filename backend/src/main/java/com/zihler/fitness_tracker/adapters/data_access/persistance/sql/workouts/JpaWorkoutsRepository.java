package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.workouts;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.exceptions.WorkoutNotFoundException;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.workouts.rowtypes.WorkoutRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWorkoutsRepository extends JpaRepository<WorkoutRow, String> {
    default WorkoutRow findByIdOrThrow(String id) {
       return findById(id).orElseThrow(()-> new WorkoutNotFoundException("Could not find workout with ID " + id));
    }
}

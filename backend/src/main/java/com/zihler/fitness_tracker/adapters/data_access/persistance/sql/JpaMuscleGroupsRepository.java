package com.zihler.fitness_tracker.adapters.data_access.persistance.sql;

import com.zihler.fitness_tracker.adapters.data_access.exceptions.MuscleGroupNotFoundException;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.MuscleGroupRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMuscleGroupsRepository
        extends JpaRepository<MuscleGroupRow, Long> {

    Optional<MuscleGroupRow> findByName(String name);

    default MuscleGroupRow findByNameOrThrow(String muscleGroupName) {
        return findByName(muscleGroupName)
                .orElseThrow(() -> new MuscleGroupNotFoundException("Could not find Muscle Group with name " + muscleGroupName));
    }
}


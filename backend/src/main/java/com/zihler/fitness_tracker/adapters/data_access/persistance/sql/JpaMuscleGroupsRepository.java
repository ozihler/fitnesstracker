package com.zihler.fitness_tracker.adapters.data_access.persistance.sql;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.MuscleGroupRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMuscleGroupsRepository
        extends JpaRepository<MuscleGroupRow, Long> {

    Optional<MuscleGroupRow> findByName(String name);
}


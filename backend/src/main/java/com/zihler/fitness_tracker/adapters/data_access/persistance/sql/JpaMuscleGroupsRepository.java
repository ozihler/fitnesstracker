package com.zihler.fitness_tracker.adapters.data_access.persistance.sql;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.MuscleGroupRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMuscleGroupsRepository
        extends JpaRepository<MuscleGroupRow, Long> {

}


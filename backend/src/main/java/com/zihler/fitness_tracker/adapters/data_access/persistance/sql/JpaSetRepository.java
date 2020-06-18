package com.zihler.fitness_tracker.adapters.data_access.persistance.sql;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows.SetRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSetRepository extends JpaRepository<SetRow, Long> {
}

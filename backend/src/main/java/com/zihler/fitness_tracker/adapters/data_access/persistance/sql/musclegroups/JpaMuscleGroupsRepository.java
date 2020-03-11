package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.exceptions.MuscleGroupNotFoundException;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.entities.MuscleGroupRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMuscleGroupsRepository extends JpaRepository<MuscleGroupRow, String> {
    MuscleGroupRow findByName(String name);

    default MuscleGroupRow findByNameOnlySelectableOrThrow(String name) {
        MuscleGroupRow muscleGroupRow = findByName(name);
        if (muscleGroupRow == null || !muscleGroupRow.isSelectable()) {
            throw new MuscleGroupNotFoundException("Could not find muscle group with name " + name);
        }
        return muscleGroupRow;
    }

}

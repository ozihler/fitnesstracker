package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.exceptions.MuscleGroupNotFoundException;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.rowtypes.MuscleGroupRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface JpaMuscleGroupsRepository extends JpaRepository<MuscleGroupRow, String> {
    MuscleGroupRow findByName(String name);

    default MuscleGroupRow findByNameOnlySelectableOrThrow(String name) {
        MuscleGroupRow muscleGroupRow = findByName(name);
        if (muscleGroupRow == null || !muscleGroupRow.isSelectable()) {
            throw new MuscleGroupNotFoundException("Could not find muscle group with name " + name);
        }
        return muscleGroupRow;
    }

    default List<MuscleGroupRow> findAllSelectable(){
        return findAll().stream()
                .filter(MuscleGroupRow::isSelectable)
                .collect(toList());
    }
}

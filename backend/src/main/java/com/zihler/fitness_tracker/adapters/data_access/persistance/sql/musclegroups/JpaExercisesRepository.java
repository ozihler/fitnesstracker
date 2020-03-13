package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.exceptions.ExerciseNotFoundException;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.rowtypes.ExerciseRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface JpaExercisesRepository extends JpaRepository<ExerciseRow, String> {
    ExerciseRow findByName(String name);

    default ExerciseRow findByNameOrThrow(String name) {
        ExerciseRow row = findByName(name);
        if (row == null || !row.isSelectable()) {
            throw new ExerciseNotFoundException("Could not find exercise with name " + name);
        }
        return row;
    }

    default List<ExerciseRow> findByMuscleGroup(String muscleGroupName) {
        return findAll().stream()
                .filter(ExerciseRow::isSelectable)
                .filter(e -> e.getMuscleGroup().getName().equalsIgnoreCase(muscleGroupName))
                .collect(toList());
    }
}

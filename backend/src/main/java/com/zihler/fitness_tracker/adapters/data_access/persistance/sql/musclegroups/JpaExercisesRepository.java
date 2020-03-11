package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.exceptions.ExerciseNotFoundInJpaRepositoryException;
import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.entities.ExerciseRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
public interface JpaExercisesRepository extends JpaRepository<ExerciseRow, String> {
    ExerciseRow findByName(String name);

    default ExerciseRow findByNameOrThrow(String name) {
        ExerciseRow row = findByName(name);
        if (row == null) {
            throw new ExerciseNotFoundInJpaRepositoryException("Could not find exercise with name " + name);
        }
        return row;
    }

    default List<ExerciseRow> findByMuscleGroup(String muscleGroupName) {
       return findAll().stream()
                .filter(e -> e.getMuscleGroup().getName().equalsIgnoreCase(muscleGroupName))
                .collect(toList());
    }
}
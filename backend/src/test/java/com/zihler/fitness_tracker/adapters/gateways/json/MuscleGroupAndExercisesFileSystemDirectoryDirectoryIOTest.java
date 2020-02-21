package com.zihler.fitness_tracker.adapters.gateways.json;

import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.musclegroups.MuscleGroupAndExercisesFileSystemDirectory;
import com.zihler.fitness_tracker.domain.values.Exercise;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Name;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.zihler.fitness_tracker.domain.values.MuscleGroups.muscleGroups;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MuscleGroupAndExercisesFileSystemDirectoryDirectoryIOTest {
    @Test
    void testWriteReadMuscleGroupsToFileSystem() {
        var fs = MuscleGroupAndExercisesFileSystemDirectory.makeDirectory();

        var toStore = muscleGroups(
                MuscleGroup.withoutSets("Chest", "Bench Press", "Push Ups", "Incline Dumbbell Bench Press"),
                MuscleGroup.withoutSets("Shoulders", "Arnold Press", "Side Lifts", "Wide Barbell Lift"),
                MuscleGroup.withoutSets("Triceps", "Lat Pull", "Overhead Lat Pull", "Dips"));

        var stored = fs.save(toStore);

        var muscleGroups = fs.fetch();

        var storedMGs = muscleGroups.getMuscleGroups();
        assertEquals(3, storedMGs.size());
        List<String> storedMuscleGroupNames = storedMGs.stream().map(MuscleGroup::getName).map(Name::toString).collect(Collectors.toList());
        assertTrue(storedMuscleGroupNames.containsAll(List.of("Chest", "Shoulders", "Triceps")));


        storedMGs.stream()
                .map(MuscleGroup::getExercises)
                .map(Exercises::getExercises)
                .flatMap(Collection::stream)
                .map(Exercise::getName)
                .map(Name::toString)
                .collect(Collectors.toList())
                .containsAll(List.of(
                        "Bench Press", "Push Ups", "Incline Dumbbell Bench Press",
                        "Arnold Press", "Side Lifts", "Wide Barbell Lift",
                        "Lat Pull", "Overhead Lat Pull", "Dips"
                ));
    }

}

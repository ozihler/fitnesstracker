package com.zihler.fitness_tracker.adapters.gateways.json;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.musclegroups.MuscleGroupAndExercisesFileSystemDirectory;
import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.entities.ExerciseFactory;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Exercises;
import com.zihler.fitness_tracker.domain.values.Name;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.zihler.fitness_tracker.domain.values.MuscleGroups.muscleGroups;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MuscleGroupAndExercisesFileSystemDirectoryDirectoryIOTest {
    @Test
    void testWriteReadMuscleGroupsToFileSystem() {
        var fs = MuscleGroupAndExercisesFileSystemDirectory.mkDir("test-muscleGroupsAndExercises");

        final String[] strings = new String[]{"Bench Press", "Push Ups", "Incline Dumbbell Bench Press"};
        final Exercises of = Exercises.of(Arrays.stream(strings).map(ExerciseFactory::createExerciseFrom).collect(toList()));
        final String[] strings1 = new String[]{"Arnold Press", "Side Lifts", "Wide Barbell Lift"};
        final Exercises of1 = Exercises.of(Arrays.stream(strings1).map(ExerciseFactory::createExerciseFrom).collect(toList()));
        final String[] strings2 = new String[]{"Lat Pull", "Overhead Lat Pull", "Dips"};
        final Exercises of2 = Exercises.of(Arrays.stream(strings2).map(ExerciseFactory::createExerciseFrom).collect(toList()));
        var toStore = muscleGroups(
                new MuscleGroup(Name.of("Chest"), of),
                new MuscleGroup(Name.of("Shoulders"), of1),
                new MuscleGroup(Name.of("Triceps"), of2));

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

        fs.clearFolder();
    }

}

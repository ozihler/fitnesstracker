package com.zihler.fitness_tracker.domain.values;

import java.util.regex.Pattern;

public class ExerciseInput {
    public static Exercise of(String name) {
        name = name.trim();
        // note: a single wielding exercise can be either dumbbell or barbell,
        // whereas it gets pretty adventurous to wield two barbells at the same time ;)
        // So double wielding are >>always<< dumbbell for now.
        if (isSingleWieldingExercise(name)) {
            return of(Name.of(name), Sets.empty(), Multiplier.defaultValue());
        } else {
            return dualWieldingDumbbellExercise(name);
        }
    }

    private static boolean isSingleWieldingExercise(String inputExerciseName) {
        return !Pattern.compile("^2#.+")
                .matcher(inputExerciseName)
                .find();
    }

    private static Exercise dualWieldingDumbbellExercise(String inputExerciseName) {
        String[] nameParts = inputExerciseName.split("#");
        Multiplier multiplier = Multiplier.of(nameParts[0]);
        Name exerciseName = Name.of(nameParts[1]);
        return new Exercise(exerciseName, Sets.empty(), multiplier);
    }

    public static Exercise of(Name name, Sets sets, Multiplier multiplier) {
        return new Exercise(name, sets, multiplier);
    }
}

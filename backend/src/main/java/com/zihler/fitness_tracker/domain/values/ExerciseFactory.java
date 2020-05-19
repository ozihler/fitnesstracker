package com.zihler.fitness_tracker.domain.values;

import java.util.regex.Pattern;

public class ExerciseFactory {
    public static Exercise createExerciseFrom(String inputValue) {
        inputValue = inputValue.trim();
        // note: a single wielding exercise can be either dumbbell or barbell,
        // whereas it gets pretty adventurous to wield two barbells at the same time ;)
        // So double wielding are >>always<< dumbbell for now.
        if (isSingleWieldingExercise(inputValue)) {
            return new Exercise(Name.of(inputValue), Sets.empty(), Multiplier.ofOne());
        } else {
            return dualWieldingDumbbellExercise(inputValue);
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

}

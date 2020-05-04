package com.zihler.fitness_tracker.domain.values;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExerciseTest {

    @Test
    void testNameCorrect() {
        Exercise testee = ExerciseFactory.createExerciseFrom("hello");
        assertEquals(Name.of("Hello"), testee.getName());
        assertEquals(Multiplier.defaultValue(), testee.getMultiplier());
    }

    @Test
    void testTrimInput() {
        Exercise testee = ExerciseFactory.createExerciseFrom(" 2#hello");
        assertEquals(Name.of("Hello"), testee.getName());
        assertEquals(Multiplier.of(2), testee.getMultiplier());
    }

    @Test
    void testExtractMuliplierFromInputString() {
        Exercise testee = ExerciseFactory.createExerciseFrom("2#hello");
        assertEquals(Name.of("hello"), testee.getName());
        assertEquals(Multiplier.of("2"), testee.getMultiplier());
    }

    @Test
    void testPatternOnlyWorksIfHashTagIsRightAfterNumberAtTheBeginning() {
        Exercise testee = ExerciseFactory.createExerciseFrom("#2#hello");
        assertEquals(Name.of("#2#hello"), testee.getName());
        assertEquals(Multiplier.defaultValue(), testee.getMultiplier());
    }

    @Test
    void testPatternDoesNotWorkForNumbersUnequal2FollowedByHashtag() {
        Exercise testee = ExerciseFactory.createExerciseFrom("1#hello");
        assertEquals(Name.of("1#hello"), testee.getName());
        assertEquals(Multiplier.defaultValue(), testee.getMultiplier());

        testee = ExerciseFactory.createExerciseFrom("3#hello");
        assertEquals(Name.of("3#hello"), testee.getName());
        assertEquals(Multiplier.defaultValue(), testee.getMultiplier());
    }

    @Test
    void testPatternDoesNotWorkForCharactersFollowedByHashtag() {
        Exercise testee = ExerciseFactory.createExerciseFrom("x#hello");
        assertEquals(Name.of("x#hello"), testee.getName());
        assertEquals(Multiplier.defaultValue(), testee.getMultiplier());
    }

    @Test
    void testPatternDoesNotWorkForDecimalNumbersFollowedByHashtag() {
        Exercise testee = ExerciseFactory.createExerciseFrom("1.2#hello");
        assertEquals(Name.of("1.2#hello"), testee.getName());
        assertEquals(Multiplier.defaultValue(), testee.getMultiplier());
    }
}

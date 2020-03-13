package com.zihler.fitness_tracker.domain.values;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    @Test
    void capitaliseWorks() {
        Name expected = Name.of("Bench Press");
        assertEquals(expected, Name.of("beNcH pRess"));
        assertEquals(expected, Name.of("bENCH pRESS"));
        assertEquals(expected, Name.of("beNCH PRess"));
        assertEquals(expected, Name.of("BeNcH PRess"));
        assertEquals(expected, Name.of("Bench Press"));
        assertEquals(expected, Name.of("bencH presS"));
    }

}
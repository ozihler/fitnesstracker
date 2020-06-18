package com.zihler.fitness_tracker.adapters.data_access.data_loading;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonToSqlInserts {
    public static void main(String[] args) throws IOException {
        System.out.println(new JsonToSqlInserts().toWorkoutJsons());
    }

    private WorkoutsJson toWorkoutJsons() throws IOException {
        var objectMapper = new ObjectMapper();

        return objectMapper.readValue(
                this.getClass().getResource("/data/json/workouts_until_18_06_2020.json"),
                WorkoutsJson.class);
    }
}

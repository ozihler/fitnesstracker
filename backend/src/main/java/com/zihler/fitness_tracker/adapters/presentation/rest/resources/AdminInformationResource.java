package com.zihler.fitness_tracker.adapters.presentation.rest.resources;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.musclegroups.MuscleGroupAndExercisesFileSystemDirectory;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.workouts.WorkoutFileSystemDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminInformationResource {

    private final long instantiationTime;

    @Autowired
    public AdminInformationResource() {
        this.instantiationTime = System.currentTimeMillis();
    }

    @GetMapping("/api/admin/instantiationTime")
    public long instantiationTime() {
        return this.instantiationTime;
    }

    @GetMapping("/api/admin/purge")
    public void purgeEntries() {
        MuscleGroupAndExercisesFileSystemDirectory.mkDir("muscleGroupsAndExercises").clearFolder();
        WorkoutFileSystemDirectory.mkDir("workouts").clearFolder();
    }
}

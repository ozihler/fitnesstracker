package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.workouts.rowtypes;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.rowtypes.MuscleGroupRow;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "workout")
public class WorkoutRow {
    @Id
    private String id;
    private String title;

    @ManyToMany
    @JoinTable(
            name="workout_musclegroup",
            joinColumns = @JoinColumn(name="workout_id"),
            inverseJoinColumns = @JoinColumn(name="muscle_group_name")
    )
    private List<MuscleGroupRow> muscleGroups;

    public WorkoutRow() {
    }
}

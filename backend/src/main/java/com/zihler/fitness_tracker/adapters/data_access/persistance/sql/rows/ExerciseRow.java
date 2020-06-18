package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "exercise")
public class ExerciseRow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "multiplier", nullable = false)
    private int multiplier;

    @Column(name = "is_selectable")
    private boolean isSelectable;

    @ManyToOne
    @JoinColumn(name = "muscle_group_id", nullable = false)
    private MuscleGroupRow muscleGroup;

    @OneToMany(mappedBy = "exercise")
    private List<SetRow> sets;

    public ExerciseRow() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }


    public MuscleGroupRow getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(MuscleGroupRow muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public List<SetRow> getSets() {
        return sets;
    }

    public void setSets(List<SetRow> sets) {
        this.sets = sets;
        sets.forEach(setRow -> setRow.setExercise(this));
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public void setSelectable(boolean selectable) {
        isSelectable = selectable;
    }
}

package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "muscle_group_id", nullable = false)
    private MuscleGroupRow muscleGroup;

    @OneToMany(mappedBy = "exercise",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<SetRow> sets = new ArrayList<>();

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
        this.muscleGroup.getExercises().add(this);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseRow that = (ExerciseRow) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.exceptions.IllegalNameException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

import java.util.Objects;

public class Name implements Comparable<Name> {

    private final String name;

    private Name(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalNameException("Name cannot be empty!");
        }
        this.name = WordUtils.capitalize(name.trim().toLowerCase(), ' ');
    }

    public static Name of(String name) {
        return new Name(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return name.equalsIgnoreCase(name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Name name) {
        return this.name.toLowerCase().compareTo(name.name.toLowerCase());
    }
}

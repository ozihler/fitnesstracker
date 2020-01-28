package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups;


import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.domain.values.Name;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class MuscleGroupsInputTest {
    @Test
    void test() {
        var request = new CreateMuscleGroupsRequest();
        request.setMuscleGroupNames("a; b, C D.E");
        var input = new MuscleGroupsInput(request);

        assertThat(input.muscleGroups())
                .extracting(muscleGroupsDocument -> muscleGroupsDocument.getMuscleGroups().size())
                .isEqualTo(5);

        Set<MuscleGroupDocument> muscleGroups = input.muscleGroups().getMuscleGroups();

        assertThat(muscleGroups).contains(MuscleGroupDocument.of(Name.of("a")));
        assertThat(muscleGroups).contains(MuscleGroupDocument.of(Name.of("b")));
        assertThat(muscleGroups).contains(MuscleGroupDocument.of(Name.of("C")));
        assertThat(muscleGroups).contains(MuscleGroupDocument.of(Name.of("D")));
        assertThat(muscleGroups).contains(MuscleGroupDocument.of(Name.of("E")));


    }
}

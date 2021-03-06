package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups;


import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.inputs.MuscleGroupsInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.requests.CreateMuscleGroupsRequest;
import com.zihler.fitness_tracker.application.outbound_ports.documents.MuscleGroupDocument;
import com.zihler.fitness_tracker.domain.values.Name;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MuscleGroupsInputTest {
    @Test
    void testNameCreationFromInputMuscleGroupNamesString() {
        var request = new CreateMuscleGroupsRequest();
        request.setMuscleGroupNames("a; b, C D.E");
        var input = new MuscleGroupsInput(request);

        assertThat(input.muscleGroups())
                .extracting(muscleGroupsDocument -> muscleGroupsDocument.getMuscleGroups().size())
                .isEqualTo(4);

        List<MuscleGroupDocument> muscleGroups = input.muscleGroups().getMuscleGroups();

        assertThat(muscleGroups).contains(MuscleGroupDocument.of(Name.of("a")));
        assertThat(muscleGroups).contains(MuscleGroupDocument.of(Name.of("b")));
        assertThat(muscleGroups).contains(MuscleGroupDocument.of(Name.of("C D")));
        assertThat(muscleGroups).contains(MuscleGroupDocument.of(Name.of("E")));
    }
}

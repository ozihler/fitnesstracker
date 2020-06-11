package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.workouts.InMemoryWorkoutRepository;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutIdRequest;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutIdViewModel;
import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.CreationDate;
import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.Sets;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.fixtures.WorkoutFixtures;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class CopyWorkoutControllerTest {

    @Test
    void canCopyWorkout() {
        Workout fullWorkout = WorkoutFixtures.FULL_WORKOUT;

        InMemoryWorkoutRepository repo = new InMemoryWorkoutRepository();
        repo.withValues(fullWorkout);

        CopyWorkoutController controller = new CopyWorkoutController(repo, repo, repo);

        WorkoutIdRequest request = new WorkoutIdRequest();
        request.setWorkoutId(fullWorkout.getWorkoutId().toString());

        ResponseEntity<WorkoutIdViewModel> response = controller.copyWorkout(request);
        WorkoutIdViewModel copiedWorkoutAsViewModel = response.getBody();

        assertNotEquals(fullWorkout.getWorkoutId().toString(), copiedWorkoutAsViewModel.getWorkoutId());

        // check stored workout in repo
        Workout copyOfWorkouts = repo.by(WorkoutId.of(copiedWorkoutAsViewModel.getWorkoutId()));

        assertTrue(fullWorkout.getCreationDate().isBefore(copyOfWorkouts.getCreationDate()));
        assertEquals(CreationDate.now(), copyOfWorkouts.getCreationDate());

        for (int i = 0; i < fullWorkout.getMuscleGroups().getMuscleGroups().size(); ++i) {
            MuscleGroup muscleGroupOfFullWorkout = fullWorkout.getMuscleGroups().getMuscleGroups().get(i);
            MuscleGroup muscleGroupOfCopy = copyOfWorkouts.getMuscleGroups().getMuscleGroups().get(i);
            assertEquals(muscleGroupOfFullWorkout.getName(), muscleGroupOfCopy.getName());
            assertEquals(muscleGroupOfFullWorkout.getExercises(), muscleGroupOfCopy.getExercises());
            List<Exercise> exercisesOfFullWorkout = muscleGroupOfFullWorkout.getExercises().getExercises();
            List<Exercise> exercisesOfCopy = muscleGroupOfCopy.getExercises().getExercises();

            assertEquals(muscleGroupOfFullWorkout.getExercises(), muscleGroupOfCopy.getExercises());

            for (int j = 0; j < exercisesOfFullWorkout.size(); ++j) {
                Sets setsOfFullWorkout = exercisesOfFullWorkout.get(j).getSets();
                Sets setsOfCopy = exercisesOfCopy.get(j).getSets();
                assertEquals(setsOfFullWorkout, setsOfCopy);
            }
        }
    }

    private Map<Name, List<Exercise>> getExercisesOfMuscleGroups(Workout workout) {
        Map<Name, List<Exercise>> map = new HashMap<>();
        workout.getMuscleGroups().getMuscleGroups().forEach(
                m -> map.put(m.getName(), m.getExercises().getExercises())
        );
        return map;
    }

    private List<Name> getMuscleGroupNames(Workout workout) {
        return workout.getMuscleGroups().getMuscleGroups().stream().map(MuscleGroup::getName).collect(toList());
    }
}

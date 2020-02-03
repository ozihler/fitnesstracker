import {WorkoutTree} from "./workout-tree";
import {Type} from "../shared/type";
import {Workout} from "../shared/workout";
import {GID} from "../shared/gid";
import {Set} from "../shared/set";
import {MuscleGroup} from "../shared/muscle-group";
import {Exercise} from "../shared/exercise";

describe('Workout Tree', () => {


  it('should have a workout node as tree', () => {
    let workout = new Workout(GID.from(1), new Date(Date.now()), "Workout", []);
    let workoutTree = new WorkoutTree(workout);
    expect(workoutTree.root.type).toBe(Type.Workout);
  });

  it('can add nodes to tree', () => {
    let workout = new Workout(GID.from(1), new Date(Date.now()), "Workout", []);
    let workoutTree = new WorkoutTree(workout);

    let muscleGroup = new MuscleGroup(undefined, "Chest", []);
    workoutTree.addNode(muscleGroup);

    let nodeOfMuscleGroup = workoutTree.findNodeByName("Chest");
    expect(nodeOfMuscleGroup.name).toBe("Chest");
    expect(nodeOfMuscleGroup.type).toBe(Type.Muscle_Group);
    expect(nodeOfMuscleGroup.parent.name).toBe(workout.name);

    let exercise = new Exercise("Bench Press", muscleGroup, []);
    workoutTree.addNode(exercise);

    let nodeOfExercise = workoutTree.findNodeByName("Bench Press");
    expect(nodeOfExercise.name).toBe("Bench Press");
    expect(nodeOfExercise.type).toBe(Type.Exercise);
    expect(nodeOfExercise.parent.name).toBe(muscleGroup.name);


    let set = new Set(GID.from(2), 50, 'kg', 12, 45, 's', exercise);
    workoutTree.addNode(set);

    let setInExercise = workoutTree.findNodeByName(Set.formatEntries(12, 50, 'kg', 45, 's'));
    expect(setInExercise.parent.name).toBe(exercise.name);
    expect(setInExercise.type).toBe(Type.Set);
  });
});

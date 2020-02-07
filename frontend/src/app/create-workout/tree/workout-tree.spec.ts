import {WorkoutTree} from "./workout-tree";
import {Type} from "../../shared/type";
import {Workout} from "../../shared/workout";
import {WorkoutId} from "../../shared/workoutId";
import {Set} from "../../shared/set";
import {MuscleGroup} from "../../shared/muscle-group";
import {Exercise} from "../../shared/exercise";
import {TreeNode} from "./tree-node";

describe('Workout Tree', () => {


  it('should have a workout node as tree', () => {
    let workout = new Workout(WorkoutId.from(1), new Date(Date.now()), "Workout", []);
    let workoutTree = new WorkoutTree(workout);
    expect(workoutTree.root.type).toBe(Type.Workout);
  });

  it('can add nodes to tree', () => {
    let workout = new Workout(WorkoutId.from(1), new Date(Date.now()), "Workout", []);
    let workoutTree = new WorkoutTree(workout);

    let muscleGroup = new MuscleGroup(undefined, "Chest", []);
    workoutTree.addNode(muscleGroup);

    let nodeOfMuscleGroup = workoutTree.findNodeByName("Chest");
    expect(nodeOfMuscleGroup.name).toBe("Chest");
    expect(nodeOfMuscleGroup.type).toBe(Type.Muscle_Group);
    expect(nodeOfMuscleGroup.parent.name).toBe(workout.name);

    let exercise = new Exercise(muscleGroup, "Bench Press", []);
    workoutTree.addNode(exercise);

    let nodeOfExercise = workoutTree.findNodeByName("Bench Press");
    expect(nodeOfExercise.name).toBe("Bench Press");
    expect(nodeOfExercise.type).toBe(Type.Exercise);
    expect(nodeOfExercise.parent.name).toBe(muscleGroup.name);


    let set = new Set(WorkoutId.from(2), 50, 'kg', 12, 45, 's', exercise);
    workoutTree.addNode(set);

    let setInExercise = workoutTree.findNodeByName(Set.formatEntries(12, 50, 'kg', 45, 's'));
    expect(setInExercise.parent.name).toBe(exercise.name);
    expect(setInExercise.type).toBe(Type.Set);
  });

  it('should enable all parents of a node identified by name', () => {
    /*
                             Workout
                            /      \
                      Chest         Triceps
                      / \               /  \
            BenchPress  DumbbellBP  LatPull OverheadLatPull
               /\          /        /  \         \
             set set     set      set  set       set
     */
    let workout = new Workout(WorkoutId.from(1), new Date(Date.now()), "Workout", []);

    let chest = new MuscleGroup(workout, "Chest", []);
    let benchPress = new Exercise(chest, "Bench Press", []);
    let set = new Set(WorkoutId.from(2), 50, 'kg', 12, 45, 's', benchPress);
    let set1 = new Set(WorkoutId.from(2), 50, 'kg', 12, 45, 's', benchPress);

    let dumbbellBP = new Exercise(chest, "Dumbbell Bench Press", []);
    let set2 = new Set(WorkoutId.from(3), 50, 'kg', 12, 45, 's', dumbbellBP);

    let triceps = new MuscleGroup(workout, "Triceps", []);
    let latPull = new Exercise(triceps, "Lat Pull", []);
    let set3 = new Set(WorkoutId.from(5), 20, 'kg', 12, 45, 's', latPull);
    let set4 = new Set(WorkoutId.from(6), 15, 'kg', 12, 45, 's', latPull);

    let overheadLatPull = new Exercise(triceps, "Overhead Lat Pull", []);
    let set5 = new Set(WorkoutId.from(7), 10, 'kg', 12, 45, 's', overheadLatPull);

    let workoutTree = new WorkoutTree(workout);
    workoutTree.addNode(chest);
    workoutTree.addNode(triceps);
    workoutTree.addNode(benchPress);
    workoutTree.addNode(dumbbellBP);
    workoutTree.addNode(latPull);
    workoutTree.addNode(overheadLatPull);
    workoutTree.addNode(set);
    workoutTree.addNode(set1);
    workoutTree.addNode(set2);
    workoutTree.addNode(set3);
    workoutTree.addNode(set4);
    workoutTree.addNode(set5);
    workoutTree.disableAllNodes();

    assertDisabled([workout, chest, benchPress, set, set1, dumbbellBP, set2, triceps, latPull, set3, set4, overheadLatPull, set5]);

    /*
    Enable Workout should enable  Chest, Workout, Triceps
                           -Workout
                          /        \
                    [x]Chest         -Triceps
                   /  \              /     \
         BenchPress -DumbbellBP  LatPull OverheadLatPull
             /\          /          /  \         \
          set set1    set2       set3  set4      set5
   */

    workoutTree.enable(workout.name);
    assertDisabled([benchPress, set, set1, dumbbellBP, set2, latPull, set3, set4, overheadLatPull, set5]);
    assertEnabled([workout, chest, triceps]);
    //
    //  /*
    //  Enable Chest should enable BenchPress, DumbbellBP, Chest, Workout, Triceps
    //                         -Workout
    //                        /        \
    //                  [x]Chest         -Triceps
    //                 /  \              /     \
    //       -BenchPress -DumbbellBP  LatPull OverheadLatPull
    //           /\          /          /  \         \
    //        set set1    set2       set3  set4      set5
    // */
    //
    workoutTree.enable(chest.name);
    assertDisabled([set, set1, set2, latPull, set3, set4, overheadLatPull, set5]);
    assertEnabled([workout, chest, triceps, benchPress, dumbbellBP]);
    //  /*
    //  Enable BenchPress should enable set, set1, DumbbellBP, Chest, BenchPress, Triceps, and Workout
    //                         -Workout
    //                        /        \
    //                  -Chest          -Triceps
    //                 /    \              /     \
    //       [x]BenchPress -DumbbellBP  LatPull OverheadLatPull
    //           /\          /          /  \         \
    //      -set -set1    set2       set3  set4      set5
    // */
    //
    workoutTree.enable(benchPress.name);
    assertDisabled([set2, latPull, set3, set4, overheadLatPull, set5]);
    assertEnabled([workout, chest, dumbbellBP, set, set1, benchPress, triceps]);
    //
    //
    //  /*
    //  Enable set4 should enable  set4, set3, LatPull, Triceps and Workout
    //                         -Workout
    //                        /      \
    //                  -Chest        -Triceps
    //                 /  \               /  \
    //       BenchPress DumbbellBP  -LatPull -OverheadLatPull
    //           /\          /        /  \         \
    //        set set1    set2    -set3  set4[x]   set5
    // */
    workoutTree.enable(set4.name);
    assertDisabled([benchPress, set, set1, dumbbellBP, set2, set5]);
    assertEnabled([workout, chest, triceps, latPull, set3, set4, overheadLatPull]);
  });

  function assertEnabled(nodes: TreeNode[]) {
    assertNodes(nodes, true);
  }

  function assertDisabled(nodes: TreeNode[]) {
    assertNodes(nodes, false);
  }

  function assertNodes(nodes: TreeNode[], expected) {
    nodes.forEach(node => {
      expect(node.isEnabled).toBe(expected, node.name)
    });
  }
});


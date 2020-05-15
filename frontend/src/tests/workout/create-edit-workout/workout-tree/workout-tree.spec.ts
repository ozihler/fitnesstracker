import {WorkoutTree} from "../../../../app/workout/create-edit-workout/workout-tree/workout-tree";
import {Type} from "../../../../app/workout/shared/type";
import {Workout} from "../../../../app/workout/shared/workout";
import {WorkoutId} from "../../../../app/workout/shared/workout-id";
import {Set} from "../../../../app/workout/shared/set";
import {MuscleGroup} from "../../../../app/workout/shared/muscle-group";
import {Exercise} from "../../../../app/workout/shared/exercise";
import {TreeNode} from "../../../../app/workout/create-edit-workout/workout-tree/tree-node";

describe('Workout Tree', () => {


  it('should have a workout node as tree', () => {
    let workout = new Workout(WorkoutId.from("1"), new Date(Date.now()), "Workout", []);
    let workoutTree = new WorkoutTree(workout);
    expect(workoutTree.root.type).toBe(Type.Workout);
  });

  it('can add nodes to tree', () => {
    let workout = new Workout(WorkoutId.from("1"), new Date(Date.now()), "Workout", []);
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


    let set = new Set(50, 'kg', 12, 45, 's', exercise);
    workoutTree.addNode(set);

    let setInExercise = workoutTree.findNodeByName(Set.formatEntries(12, 50, 'kg', 45, 's'));
    expect(setInExercise.parent.name).toBe(exercise.name);
    expect(setInExercise.type).toBe(Type.Set);
  });

  it('should correctly delete sets', () => {

      /*
                               Workout
                              /      \
                        Chest         Triceps
                        / \               /  \
              BenchPress  DumbbellBP  LatPull OverheadLatPull
                 /\          /        /  \         \
               set set1    set2   set3  set4      set5
       */
      let {workout, chest, benchPress, set, set1, dumbbellBP, set2, triceps, latPull, set3, set4, overheadLatPull, set5, workoutTree} = createSimpleTree();
      workoutTree.select(set.name);


      expect(workoutTree.findNodeByName(set2.name)).toBeDefined();
      expect(workoutTree.findNodeByName(latPull.name)).toBeDefined();
      expect(workoutTree.findNodeByName(set3.name)).toBeDefined();
      expect(workoutTree.findNodeByName(set4.name)).toBeDefined();
      expect(workoutTree.findNodeByName(overheadLatPull.name)).toBeDefined();
      expect(workoutTree.findNodeByName(set5.name)).toBeDefined();
      expect(workoutTree.findNodeByName(workout.name)).toBeDefined();

      expect(workoutTree.findNodeByName(chest.name)).toBeDefined();
      expect(workoutTree.findNodeByName(triceps.name)).toBeDefined();
      expect(workoutTree.findNodeByName(benchPress.name)).toBeDefined();
      expect(workoutTree.findNodeByName(dumbbellBP.name)).toBeDefined();
      expect(workoutTree.findNodeByName(set1.name)).toBeDefined();
      expect(workoutTree.findNodeByName(set.name).isEnabled).toBe(true)

      workoutTree.delete(set.name);

      expect(workoutTree.findNodeByName(set2.name)).toBeDefined();
      expect(workoutTree.findNodeByName(latPull.name)).toBeDefined();
      expect(workoutTree.findNodeByName(set3.name)).toBeDefined();
      expect(workoutTree.findNodeByName(set4.name)).toBeDefined();
      expect(workoutTree.findNodeByName(overheadLatPull.name)).toBeDefined();
      expect(workoutTree.findNodeByName(set5.name)).toBeDefined();
      expect(workoutTree.findNodeByName(workout.name)).toBeDefined();

      expect(workoutTree.findNodeByName(chest.name)).toBeDefined();
      expect(workoutTree.findNodeByName(triceps.name)).toBeDefined();
      expect(workoutTree.findNodeByName(benchPress.name)).toBeDefined();
      expect(workoutTree.findNodeByName(dumbbellBP.name)).toBeDefined();
      expect(workoutTree.findNodeByName(set1.name)).toBeDefined();
      expect(workoutTree.findNodeByName(set.name)).not.toBeDefined()

    }
  );

  //Todo test what happens when there is no child in the parent after deletion > enable parent

  //todo try replacing workoutTree.findNodeByName > variable (e.g. chest)
  it('should delete muscle group and all its children', () => {

      /*
                               Workout
                              /      \
                        Chest         Triceps
                        / \               /  \
              BenchPress  DumbbellBP  LatPull OverheadLatPull
                 /\          /        /  \         \
               set set1    set2   set3  set4      set5
       */
      let {workout, chest, benchPress, set, set1, dumbbellBP, set2, triceps, latPull, set3, set4, overheadLatPull, set5, workoutTree} = createSimpleTree();
      workoutTree.select(chest.name);


      expect(workoutTree.findNodeByName(set2.name)).toBeDefined();
      expect(workoutTree.findNodeByName(set3.name)).toBeDefined();
      expect(workoutTree.findNodeByName(set4.name)).toBeDefined();
      expect(workoutTree.findNodeByName(set5.name)).toBeDefined();
      expect(workoutTree.findNodeByName(latPull.name)).toBeDefined();
      expect(workoutTree.findNodeByName(overheadLatPull.name)).toBeDefined();

      expect(workoutTree.findNodeByName(workout.name)).toBeDefined();
      expect(workoutTree.findNodeByName(chest.name)).toBeDefined();
      expect(workoutTree.findNodeByName(triceps.name)).toBeDefined();
      expect(workoutTree.findNodeByName(benchPress.name)).toBeDefined();
      expect(workoutTree.findNodeByName(dumbbellBP.name)).toBeDefined();
      expect(workoutTree.findNodeByName(set1.name)).toBeDefined();
      expect(workoutTree.findNodeByName(set.name)).toBeDefined();

      workoutTree.delete(chest.name);

      expect(workoutTree.findNodeByName(workout.name)).toBeDefined();

      // Remove chest sub tree
      expect(workoutTree.findNodeByName(chest.name)).not.toBeDefined();
      expect(workoutTree.findNodeByName(benchPress.name)).not.toBeDefined();
      expect(workoutTree.findNodeByName(dumbbellBP.name)).not.toBeDefined();
      expect(workoutTree.findNodeByName(set1.name)).not.toBeDefined();
      expect(workoutTree.findNodeByName(set2.name)).not.toBeDefined();
      expect(workoutTree.findNodeByName(set.name)).not.toBeDefined()

      // enable triceps
      expect(workoutTree.findNodeByName(triceps.name)).toBeDefined();
      expect(workoutTree.findNodeByName(latPull.name)).toBeDefined();
      expect(workoutTree.findNodeByName(overheadLatPull.name)).toBeDefined();

      // disable children of exercises of triceps
      expect(workoutTree.findNodeByName(set3.name)).toBeDefined();
      expect(workoutTree.findNodeByName(set4.name)).toBeDefined();
      expect(workoutTree.findNodeByName(set5.name)).toBeDefined();
    }
  );

  it('should correctly delete exercise', () => {

      /*
                               Workout
                              /      \
                        Chest         Triceps
                        / \               /  \
              BenchPress  DumbbellBP  LatPull OverheadLatPull
                 /\          /        /  \         \
               set set1    set2   set3  set4      set5
       */
      let {workout, chest, benchPress, set, set1, dumbbellBP, set2, triceps, latPull, set3, set4, overheadLatPull, set5, workoutTree} = createSimpleTree();
      workoutTree.select(latPull.name);

      expect(workoutTree.findNodeByName(benchPress.name)).toBeDefined();

      workoutTree.delete(benchPress.name);


      // Remove bench press sub tree
      expect(workoutTree.findNodeByName(benchPress.name)).not.toBeDefined();
      expect(workoutTree.findNodeByName(set.name)).not.toBeDefined();
      expect(workoutTree.findNodeByName(set1.name)).not.toBeDefined();

    }
  );

  it('should enable all parents of a node identified by name', () => {
    let {workout, chest, benchPress, set, set1, dumbbellBP, set2, triceps, latPull, set3, set4, overheadLatPull, set5, workoutTree} = createSimpleTree();

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

  function createSimpleTree() {
    /*
                             Workout
                            /      \
                      Chest         Triceps
                      / \               /  \
            BenchPress  DumbbellBP  LatPull OverheadLatPull
               /\          /        /  \         \
             set set1    set2   set3  set4      set5
     */
    let workout = new Workout(WorkoutId.from("1"), new Date(Date.now()), "Workout", []);

    let chest = new MuscleGroup(workout, "Chest", []);
    let benchPress = new Exercise(chest, "Bench Press", []);
    let set = new Set(50, 'kg', 12, 55, 's', benchPress);
    let set1 = new Set(50, 'kg', 12, 60, 's', benchPress);

    let dumbbellBP = new Exercise(chest, "Dumbbell Bench Press", []);
    let set2 = new Set(50, 'kg', 12, 50, 's', dumbbellBP);

    let triceps = new MuscleGroup(workout, "Triceps", []);
    let latPull = new Exercise(triceps, "Lat Pull", []);
    let set3 = new Set(20, 'kg', 12, 45, 's', latPull);
    let set4 = new Set(15, 'kg', 12, 55, 's', latPull);

    let overheadLatPull = new Exercise(triceps, "Overhead Lat Pull", []);
    let set5 = new Set(10, 'kg', 12, 40, 's', overheadLatPull);

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
    return {
      workout,
      chest,
      benchPress,
      set,
      set1,
      dumbbellBP,
      set2,
      triceps,
      latPull,
      set3,
      set4,
      overheadLatPull,
      set5,
      workoutTree
    };
  }

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

  it('can calulate the correct overall weight for a node', () => {
    let {workout, chest, benchPress, set, set1, dumbbellBP, set2, triceps, latPull, set3, set4, overheadLatPull, set5, workoutTree} = createSimpleTree();
    expect(set.cumulateWeight()).toBe(50 * 12);
    expect(set1.cumulateWeight()).toBe(50 * 12);
    expect(benchPress.cumulateWeight()).toBe(set.cumulateWeight() + set1.cumulateWeight());

    expect(set2.cumulateWeight()).toBe(50 * 12);
    expect(dumbbellBP.cumulateWeight()).toBe(set2.cumulateWeight());

    expect(chest.cumulateWeight()).toBe(benchPress.cumulateWeight() + dumbbellBP.cumulateWeight());

    expect(set3.cumulateWeight()).toBe(20 * 12);
    expect(set4.cumulateWeight()).toBe(15 * 12);
    expect(latPull.cumulateWeight()).toBe(set3.cumulateWeight() + set4.cumulateWeight());

    expect(set5.cumulateWeight()).toBe(10 * 12);
    expect(overheadLatPull.cumulateWeight()).toBe(set5.cumulateWeight());

    expect(triceps.cumulateWeight()).toBe(latPull.cumulateWeight() + overheadLatPull.cumulateWeight());
    expect(workout.cumulateWeight()).toBe(chest.cumulateWeight() + triceps.cumulateWeight());

    //todo add multiplicator for dumbbell etc. into calculation
  });

});


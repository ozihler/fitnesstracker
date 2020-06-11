import {WorkoutTree} from '../../../../app/workout/create-edit-workout/workout-tree/workout-tree';
import {Type} from '../../../../app/workout/shared/type';
import {Workout} from '../../../../app/workout/shared/workout';
import {WorkoutId} from '../../../../app/workout/shared/workout-id';
import {Set} from '../../../../app/workout/shared/set';
import {MuscleGroup} from '../../../../app/workout/shared/muscle-group';
import {Exercise} from '../../../../app/workout/shared/exercise';
import {TreeNode} from '../../../../app/workout/create-edit-workout/workout-tree/tree-node';

describe('Workout Tree', () => {


  it('should have a workout node as tree', () => {
    const workout = new Workout(WorkoutId.from('1'), new Date(Date.now()), 'Workout', []);
    const workoutTree = new WorkoutTree(workout);
    expect(workoutTree.root.typeOfCurrentlySelection).toBe(Type.Workout);
  });

  it('can add nodes to tree', () => {
    const workout = new Workout(WorkoutId.from('1'), new Date(Date.now()), 'Workout', []);
    const workoutTree = new WorkoutTree(workout);

    const muscleGroup = new MuscleGroup(undefined, 'Chest', []);
    workoutTree.addNode(muscleGroup);

    const nodeOfMuscleGroup = workoutTree.findNodeById(muscleGroup.id);
    expect(nodeOfMuscleGroup.name).toBe(muscleGroup.name);
    expect(nodeOfMuscleGroup.typeOfCurrentlySelection).toBe(muscleGroup.typeOfCurrentlySelection);
    expect(nodeOfMuscleGroup.parent.name).toBe(workout.name);

    const exercise = new Exercise(muscleGroup, 'Bench Press', []);
    workoutTree.addNode(exercise);

    const nodeOfExercise = workoutTree.findNodeById(exercise.id);
    expect(nodeOfExercise.name).toBe(exercise.name);
    expect(nodeOfExercise.typeOfCurrentlySelection).toBe(exercise.typeOfCurrentlySelection);
    expect(nodeOfExercise.parent.name).toBe(muscleGroup.name);


    const set = new Set(50, 'kg', 12, 45, 's', exercise);
    workoutTree.addNode(set);

    const setInExercise = workoutTree.findNodeById(set.id);
    expect(setInExercise.parent.name).toBe(exercise.name);
    expect(setInExercise.typeOfCurrentlySelection).toBe(set.typeOfCurrentlySelection);
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
      const {workout, chest, benchPress, set, set1, dumbbellBP, set2, triceps, latPull, set3, set4, overheadLatPull, set5, workoutTree}
        = createSimpleTree();
      workoutTree.select(set.id);


      expect(workoutTree.findNodeById(set2.id)).toBeDefined();
      expect(workoutTree.findNodeById(latPull.id)).toBeDefined();
      expect(workoutTree.findNodeById(set3.id)).toBeDefined();
      expect(workoutTree.findNodeById(set4.id)).toBeDefined();
      expect(workoutTree.findNodeById(overheadLatPull.id)).toBeDefined();
      expect(workoutTree.findNodeById(set5.id)).toBeDefined();
      expect(workoutTree.findNodeById(workout.id)).toBeDefined();

      expect(workoutTree.findNodeById(chest.id)).toBeDefined();
      expect(workoutTree.findNodeById(triceps.id)).toBeDefined();
      expect(workoutTree.findNodeById(benchPress.id)).toBeDefined();
      expect(workoutTree.findNodeById(dumbbellBP.id)).toBeDefined();
      expect(workoutTree.findNodeById(set1.id)).toBeDefined();
      expect(workoutTree.findNodeById(set.id).isEnabled).toBe(true);

      workoutTree.delete(set.id);

      expect(workoutTree.findNodeById(set2.id)).toBeDefined();
      expect(workoutTree.findNodeById(latPull.id)).toBeDefined();
      expect(workoutTree.findNodeById(set3.id)).toBeDefined();
      expect(workoutTree.findNodeById(set4.id)).toBeDefined();
      expect(workoutTree.findNodeById(overheadLatPull.id)).toBeDefined();
      expect(workoutTree.findNodeById(set5.id)).toBeDefined();
      expect(workoutTree.findNodeById(workout.id)).toBeDefined();

      expect(workoutTree.findNodeById(chest.id)).toBeDefined();
      expect(workoutTree.findNodeById(triceps.id)).toBeDefined();
      expect(workoutTree.findNodeById(benchPress.id)).toBeDefined();
      expect(workoutTree.findNodeById(dumbbellBP.id)).toBeDefined();
      expect(workoutTree.findNodeById(set1.id)).toBeDefined();
      expect(workoutTree.findNodeById(set.id)).not.toBeDefined();
      console.log('workout tree\n', workoutTree);

    }
  );

  // Todo test what happens when there is no child in the parent after deletion > enable parent

  // todo try replacing workoutTree.findNodeByName > variable (e.g. chest)
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
      const {workout, chest, benchPress, set, set1, dumbbellBP, set2, triceps, latPull, set3, set4, overheadLatPull, set5, workoutTree} = createSimpleTree();
      workoutTree.select(chest.id);


      expect(workoutTree.findNodeById(set2.id)).toBeDefined();
      expect(workoutTree.findNodeById(set3.id)).toBeDefined();
      expect(workoutTree.findNodeById(set4.id)).toBeDefined();
      expect(workoutTree.findNodeById(set5.id)).toBeDefined();
      expect(workoutTree.findNodeById(latPull.id)).toBeDefined();
      expect(workoutTree.findNodeById(overheadLatPull.id)).toBeDefined();

      expect(workoutTree.findNodeById(workout.id)).toBeDefined();
      expect(workoutTree.findNodeById(chest.id)).toBeDefined();
      expect(workoutTree.findNodeById(triceps.id)).toBeDefined();
      expect(workoutTree.findNodeById(benchPress.id)).toBeDefined();
      expect(workoutTree.findNodeById(dumbbellBP.id)).toBeDefined();
      expect(workoutTree.findNodeById(set1.id)).toBeDefined();
      expect(workoutTree.findNodeById(set.id)).toBeDefined();

      workoutTree.delete(chest.id);

      expect(workoutTree.findNodeById(workout.id)).toBeDefined();

      // Remove chest sub tree
      expect(workoutTree.findNodeById(chest.id)).not.toBeDefined();
      expect(workoutTree.findNodeById(benchPress.id)).not.toBeDefined();
      expect(workoutTree.findNodeById(dumbbellBP.id)).not.toBeDefined();
      expect(workoutTree.findNodeById(set1.id)).not.toBeDefined();
      expect(workoutTree.findNodeById(set2.id)).not.toBeDefined();
      expect(workoutTree.findNodeById(set.id)).not.toBeDefined();

      // enable triceps
      expect(workoutTree.findNodeById(triceps.id)).toBeDefined();
      expect(workoutTree.findNodeById(latPull.id)).toBeDefined();
      expect(workoutTree.findNodeById(overheadLatPull.id)).toBeDefined();

      // disable children of exercises of triceps
      expect(workoutTree.findNodeById(set3.id)).toBeDefined();
      expect(workoutTree.findNodeById(set4.id)).toBeDefined();
      expect(workoutTree.findNodeById(set5.id)).toBeDefined();
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
      const {workout, chest, benchPress, set, set1, dumbbellBP, set2, triceps, latPull, set3, set4, overheadLatPull, set5, workoutTree} = createSimpleTree();
      workoutTree.select(latPull.id);

      expect(workoutTree.findNodeById(benchPress.id)).toBeDefined();

      workoutTree.delete(benchPress.id);


      // Remove bench press sub tree
      expect(workoutTree.findNodeById(benchPress.id)).not.toBeDefined();
      expect(workoutTree.findNodeById(set.id)).not.toBeDefined();
      expect(workoutTree.findNodeById(set1.id)).not.toBeDefined();

    }
  );

  it('should enable all parents of a node identified by name', () => {
    const {workout, chest, benchPress, set, set1, dumbbellBP, set2, triceps, latPull, set3, set4, overheadLatPull, set5, workoutTree} = createSimpleTree();

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

    workoutTree.enable(workout.id);
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
    workoutTree.enable(chest.id);
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
    workoutTree.enable(benchPress.id);
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
    workoutTree.enable(set4.id);
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
    const workout = new Workout(WorkoutId.from('1'), new Date(Date.now()), 'Workout', []);

    const chest = new MuscleGroup(workout, 'Chest', []);
    const benchPress = new Exercise(chest, 'Bench Press', []);
    const set = new Set(50, 'kg', 12, 55, 's', benchPress, 0);
    const set1 = new Set(50, 'kg', 12, 60, 's', benchPress, 1);

    const dumbbellBP = new Exercise(chest, 'Dumbbell Bench Press', []);
    const set2 = new Set(50, 'kg', 12, 50, 's', dumbbellBP, 0);

    const triceps = new MuscleGroup(workout, 'Triceps', []);
    const latPull = new Exercise(triceps, 'Lat Pull', []);
    const set3 = new Set(20, 'kg', 12, 45, 's', latPull, 0);
    const set4 = new Set(15, 'kg', 12, 55, 's', latPull, 1);

    const overheadLatPull = new Exercise(triceps, 'Overhead Lat Pull', []);
    const set5 = new Set(10, 'kg', 12, 40, 's', overheadLatPull, 0);

    const workoutTree = new WorkoutTree(workout);
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
      expect(node.isEnabled).toBe(expected, node.name);
    });
  }

  it('can calulate the correct overall weight for a node', () => {
    const {workout, chest, benchPress, set, set1, dumbbellBP, set2, triceps, latPull, set3, set4, overheadLatPull, set5, workoutTree} = createSimpleTree();
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

    // todo add multiplicator for dumbbell etc. into calculation
  });

});


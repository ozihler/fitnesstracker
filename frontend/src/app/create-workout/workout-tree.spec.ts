import {WorkoutTree} from "./workout-tree";
import {Type} from "../shared/type";
import {Workout} from "../shared/workout";
import {GID} from "../shared/gid";

describe('Workout Tree', () => {


  it('should have a workout node as tree', () => {
    let workout = new Workout(GID.from(1), new Date(Date.now()), "Workout", []);
    let workoutTree = new WorkoutTree(workout);
    expect(workoutTree.root.type).toBe(Type.Workout);
  });
});

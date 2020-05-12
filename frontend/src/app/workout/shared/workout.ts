import {WorkoutId} from './workout-id';
import {TreeNode} from "../create-edit-workout/workout-tree/tree-node";
import {Type} from "./type";
import {MuscleGroup} from "./muscle-group";

export class Workout extends TreeNode {
  constructor(private _workoutId: WorkoutId,
              private _creationDate: Date,
              name: string,
              muscleGroups: MuscleGroup[] = []) {
    super(undefined, name, muscleGroups)
  }

  get type(): Type {
    return Type.Workout;
  }

  get title() {
    return this.name;
  }

  set title(value: string) {
    super.name = (value);
  }


  get workoutId(): WorkoutId {
    return this._workoutId;
  }

  set workoutId(value: WorkoutId) {
    this._workoutId = value;
  }

  set creationDate(value: Date) {
    this._creationDate = value;
  }

  get creationDate(): Date {
    return this._creationDate;
  }
}

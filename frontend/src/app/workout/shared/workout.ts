import {WorkoutId} from './workout-id';
import {TreeNode} from '../create-edit-workout/workout-tree/tree-node';
import {Type} from './type';
import {MuscleGroup} from './muscle-group';

export class Workout extends TreeNode {
// currently the only way to make sure the muscle group with the same name is selected only
  static WORKOUT_INITIAL_TITLE = 'New Workout';

  constructor(private _workoutId: WorkoutId,
              private _creationDate: Date,
              name: string,
              muscleGroups: MuscleGroup[] = []) {
    super(undefined, name, muscleGroups);
  }

  get type(): Type {
    return Type.Workout;
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

  get id(): string {
    return 'root';
  }


  get name() {
    return this._name ? this._name : Workout.WORKOUT_INITIAL_TITLE;
  }

  set name(value) {
    this._name = value;
  }
}

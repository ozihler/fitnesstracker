import {Exercise} from "./exercise";
import {TreeNode} from "../create-edit-workout/tree/tree-node";
import {Type} from "./type";
import {Workout} from "./workout";

export class MuscleGroup extends TreeNode {

  constructor(parent: Workout, name: string, exercises: Exercise[] = []) {
    super(parent, name, exercises);
  }

  get type(): Type {
    return Type.Muscle_Group;
  }

}

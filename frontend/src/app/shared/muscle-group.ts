import {Exercise} from "./exercise";
import {TreeNode} from "./tree-node";
import {Type} from "./type";
import {Workout} from "./workout";
import {WorkoutTreeVisitor} from "./workout-tree-visitor";

export class MuscleGroup extends TreeNode {

  constructor(parent: Workout, name: string, exercises: Exercise[]) {
    super(parent, name, exercises);
  }

  get type(): Type {
    return Type.Muscle_Group;
  }

  accept(visitor: WorkoutTreeVisitor) {
    visitor.visitMuscleGroup(this);
    this.children.forEach(child => child.accept(visitor));
  }

}

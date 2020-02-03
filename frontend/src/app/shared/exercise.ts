import {Set} from "./set";
import {TreeNode} from "./tree-node";
import {MuscleGroup} from "./muscle-group";
import {Type} from "./type";
import {WorkoutTreeVisitor} from "./workout-tree-visitor";

export class Exercise extends TreeNode {
  constructor(name: string, parent: MuscleGroup, sets?: Set[]) {
    super(parent, name, sets);
  }

  get type(): Type {
    return Type.Exercise;
  }

  accept(visitor: WorkoutTreeVisitor) {
    visitor.visitExercise(this);
    this.children.forEach(child => child.accept(visitor));
  }
}

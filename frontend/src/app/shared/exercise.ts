import {Set} from "./set";
import {TreeNode} from "../create-workout/tree/tree-node";
import {MuscleGroup} from "./muscle-group";
import {Type} from "./type";

export class Exercise extends TreeNode {
  constructor(parent: MuscleGroup, name: string, sets: Set[] = []) {
    super(parent, name, sets);
  }

  get type(): Type {
    return Type.Exercise;
  }
}

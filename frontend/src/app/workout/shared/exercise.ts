import {Set} from './set';
import {TreeNode} from '../create-edit-workout/workout-tree/tree-node';
import {MuscleGroup} from './muscle-group';
import {Type} from './type';

export class Exercise extends TreeNode {
  constructor(parent: MuscleGroup, name: string, sets: Set[] = [], private _multiplier = 1) {
    super(parent, name, sets);
  }

  get type(): Type {
    return Type.Exercise;
  }


  get multiplier(): number {
    return this._multiplier;
  }

  set multiplier(value: number) {
    this._multiplier = value;
  }


  cumulateWeight(): number {
    if (!this.children || this.children.length === 0) {
      return 0;
    }
    return this.children.map(node => node.cumulateWeight())
      .reduce((before, after) => before + after);
  }
}

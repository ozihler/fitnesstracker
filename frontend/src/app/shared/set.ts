import {TreeNode} from "./tree-node";
import {Type} from "./type";
import {Exercise} from "./exercise";

export class Set extends TreeNode {
  constructor(private _repetitions: number,
              private _weight: number,
              private _waitingTime: number,
              parent: Exercise) {
    super(parent, `${Set.formatEntries(_repetitions, _weight, _waitingTime)}`, undefined);
  }

  static formatEntries(repetitions, weight: number, waitingTime: number) {
    return `${repetitions}[reps] |${weight}[kg] |${waitingTime}[s]`;
  }

  get type(): Type {
    return Type.Set;
  }
}

import {TreeNode} from "../create-workout/tree/tree-node";
import {Type} from "./type";
import {Exercise} from "./exercise";
import {GID} from "./gid";

export class Set extends TreeNode {


  constructor(private _gid: GID,
              private _weight: number,
              private _weightUnit: string,
              private _numberOfRepetitions: number,
              private _waitingTime: number,
              private _waitingTimeUnit: string,
              parent: Exercise) {
    super(parent, `${Set.formatEntries(_numberOfRepetitions, _weight, _weightUnit, _waitingTime, _waitingTimeUnit)}`, undefined);
  }

  get gid(): GID {
    return this._gid;
  }

  get type(): Type {
    return Type.Set;
  }

  set gid(value: GID) {
    this._gid = value;
  }

  static formatEntries(repetitions, weight: number, unitOfMeasurement: string, waitingTime: number, unitOfTime: string) {
    return `${repetitions}[reps] |${weight}[${unitOfMeasurement}] |${waitingTime}[${unitOfTime}]`;
  }


}

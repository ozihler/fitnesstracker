import {TreeNode} from "../create-edit-workout/workout-tree/tree-node";
import {Type} from "./type";
import {Exercise} from "./exercise";

export class Set extends TreeNode {

  constructor(
    private _weight: number,
    private _weightUnit: string,
    private _numberOfRepetitions: number,
    private _waitingTime: number,
    private _waitingTimeUnit: string,
    parent: Exercise) {
    super(parent, `${Set.formatEntries(_numberOfRepetitions, _weight, _weightUnit, _waitingTime, _waitingTimeUnit)}`, undefined);
  }

  get type(): Type {
    return Type.Set;
  }

  get weight(): number {
    return this._weight;
  }

  get weightUnit(): string {
    return this._weightUnit;
  }

  get numberOfRepetitions(): number {
    return this._numberOfRepetitions;
  }

  get waitingTime(): number {
    return this._waitingTime;
  }

  get waitingTimeUnit(): string {
    return this._waitingTimeUnit;
  }

  static formatEntries(repetitions, weight: number, unitOfMeasurement: string, waitingTime: number, unitOfTime: string) {
    return `${weight} ${unitOfMeasurement}, ${repetitions} #, ${waitingTime} ${unitOfTime}`;
  }


}

import {TreeNode} from '../create-edit-workout/workout-tree/tree-node';
import {Type} from './type';
import {Exercise} from './exercise';
import {SetFormatPipe} from './pipes/set-format.pipe';

export class Set extends TreeNode {

  constructor(
    private _weight: number,
    private _weightUnit: string,
    private _numberOfRepetitions: number,
    private _waitingTime: number,
    private _waitingTimeUnit: string,
    parent: Exercise) {
    super(parent, ``, undefined);
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

  get name(): any {
    return new SetFormatPipe().transform(this);
  }

  cumulateWeight(): number {
    return this.weight * this.numberOfRepetitions /* *this.multiplier*/;
  }
}

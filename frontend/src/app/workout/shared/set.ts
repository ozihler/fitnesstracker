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
    parent: Exercise,
    private _index_in_exercise = 0) {
    super(parent, _index_in_exercise + '', undefined);
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

  get name(): string {
    return new SetFormatPipe().transform(this);
  }

  get id(): string {
    let idString = '';
    idString += this._index_in_exercise + '-';
    if (this.parent) {
      idString += this.parent.id + '-';
    }
    idString += super.id;
    return idString;
  }

  cumulateWeight(): number {
    return this.weight * this.numberOfRepetitions /* *this.multiplier*/;
  }
}

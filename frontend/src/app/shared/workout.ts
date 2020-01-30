import {GID} from './gid';
import {TreeNode} from "./tree-node";
import {Type} from "./type";
import {MuscleGroup} from "./muscle-group";

export class Workout extends TreeNode {
  constructor(private _gid: GID, private _creationDate:Date, name: string, muscleGroups: MuscleGroup[]) {
    super(undefined, name, muscleGroups)
  }

  get type(): Type {
    return Type.Workout;
  }

  get title() {
    return this.name;
  }

  get gid(): GID {
    return this._gid;
  }

  get creationDate(): Date {
    return this._creationDate;
  }
}

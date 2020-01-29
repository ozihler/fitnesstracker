import {sizeOf} from "../../shared/array-utils";
import {Type} from "../../shared/type";


export class ButtonNode {
  static LEVEL_CLASSES = {1: 'uk-button-secondary', 2: 'uk-button-primary', 3: 'uk-button-default'};

  constructor(private _name: string,
              private _children: ButtonNode[],
              private _type: Type) {
  }

  isLeaf(): boolean {
    return sizeOf(this._children) <= 0
  }

  hasChildren(): boolean {
    return !this.isLeaf();
  }

  numberOfChildren(): number {
    return sizeOf(this._children);
  }
  get name(): string {
    return this._name;
  }

  get children(): ButtonNode[] {
    return this._children;
  }


  get type(): Type {
    return this._type;
  }
}

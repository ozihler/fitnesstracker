import {Type} from "./type";
import {sizeOf} from "./array-utils";
import {WorkoutTreeVisitor} from "./workout-tree-visitor";


export abstract class TreeNode {
  static LEVEL_CLASSES = {1: 'uk-button-secondary', 2: 'uk-button-primary', 3: 'uk-button-default'};
  private _isEnabled: boolean = false;

  protected constructor(private _parent: TreeNode,
                        private _name,
                        private _children: TreeNode[]) {
  }


  get parent(): TreeNode {
    return this._parent;
  }

  get name() {
    return this._name;
  }

  get children(): TreeNode[] {
    return this._children;
  }

  get hasChildren(): boolean {
    return this.numberOfChildren > 0;
  }

  get isLeaf(): boolean {
    return this.type != Type.Set;
  }

  get isEnabled(): boolean {
    return this._isEnabled;
  }

  set isEnabled(value: boolean) {
    this._isEnabled = value;
  }

  get numberOfChildren(): number {
    return sizeOf(this.children);
  }


  set parent(value: TreeNode) {
    this._parent = value;
  }

  abstract get type(): Type;

  public abstract accept(visitor: WorkoutTreeVisitor);
}

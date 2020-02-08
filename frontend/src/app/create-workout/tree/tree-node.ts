import {Type} from "../../shared/type";
import {sizeOf} from "../../shared/array-utils";
import {NodeVisitor} from "./node.visitor";


export abstract class TreeNode {
  static LEVEL_CLASSES = {1: 'uk-button-secondary', 2: 'uk-button-primary', 3: 'uk-button-default'};
  private _isEnabled: boolean = false;

  protected constructor(private _parent: TreeNode,
                        private _name,
                        private _children: TreeNode[]) {
    this.linkToChildren();
  }


  get parent(): TreeNode {
    return this._parent;
  }

  get name() {
    return this._name;
  }

  set name(value) {
    this._name = value;
  }

  get children(): TreeNode[] {
    return this._children;
  }


  set children(value: TreeNode[]) {
    this._children = value;
  }

  get hasChildren(): boolean {
    return this.numberOfChildren > 0;
  }

  get isLeaf(): boolean {
    return this.type == Type.Set;
  }

  get isEnabled(): boolean {
    return this._isEnabled;
  }

  set isEnabled(value: boolean) {
    this._isEnabled = value;
  }

  enable(): void {
    this.isEnabled = true;
  }

  disable(): void {
    this.isEnabled = false;
  }

  get numberOfChildren(): number {
    return sizeOf(this.children);
  }


  set parent(value: TreeNode) {
    this._parent = value;
  }

  abstract get type(): Type;

  accept(visitor: NodeVisitor) {
    visitor.visit(this);
  }

  linkToParent(parent: TreeNode) {
    this.parent = parent;
    parent.children.push(this);
  }


  linkToChildren() {
    if (!this.children) {
      return;
    }
    this.children.forEach(child => child.parent = this);
  }
}

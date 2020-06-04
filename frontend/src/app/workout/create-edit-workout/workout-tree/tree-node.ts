import {Type} from '../../shared/type';
import {sizeOf} from '../../shared/array-utils';
import {NodeVisitor} from './node.visitor';
import {Cumulatable} from '../../shared/cumulatable';


export abstract class TreeNode implements Cumulatable {
  static LEVEL_CLASSES = {1: 'uk-button-secondary', 2: 'uk-button-primary', 3: 'uk-button-default'};
  private _isEnabled: boolean = false;

  protected constructor(private _parent: TreeNode,
                        private _name,
                        private _children: TreeNode[]) {
    this.linkToChildren();
  }

  get isRoot(): boolean {
    return this.type === Type.Workout;
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
    return this.type === Type.Set;
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

  get id(): string {
    return this.name
      .replace(/#+/g, '')
      .replace(/[\s\,\.]+/g, '-')
      .toLowerCase();
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


  cumulateWeight(): number {
    if (!this.children || this.children.length === 0) {
      return 0;
    }
    return this.children.map(node => node.cumulateWeight())
      .reduce((before, after) => before + after);
  }
}

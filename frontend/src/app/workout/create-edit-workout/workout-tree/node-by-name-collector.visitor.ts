import {TreeNode} from './tree-node';
import {NodeVisitor} from './node.visitor';

export class NodeByNameCollectorVisitor implements NodeVisitor {
  constructor(private _nodeId: string) {

  }

  private _foundNode: TreeNode;

  get foundNode(): TreeNode {
    return this._foundNode;
  }

  set foundNode(value: TreeNode) {
    this._foundNode = value;
  }

  get nodeId(): string {
    return this._nodeId;
  }

  visit(node: TreeNode) {
    if (node.id === this.nodeId) {
      this.foundNode = node;
      return;
    }

    if (node.children) {
      node.children.forEach(child => this.visit(child));
    }
  }

}

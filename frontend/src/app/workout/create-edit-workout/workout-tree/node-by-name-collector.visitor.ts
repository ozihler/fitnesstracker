import {TreeNode} from "./tree-node";
import {NodeVisitor} from "./node.visitor";

export class NodeByNameCollectorVisitor implements NodeVisitor {
  constructor(private _nodeName: string) {

  }

  private _foundNode: TreeNode;

  get foundNode(): TreeNode {
    return this._foundNode;
  }

  set foundNode(value: TreeNode) {
    this._foundNode = value;
  }

  get nodeName(): string {
    return this._nodeName;
  }

  visit(node: TreeNode) {
    if (node.name === this.nodeName) {
      this.foundNode = node;
    }

    if (node.children) {
      node.children.forEach(child => this.visit(child));
    }
  }

}

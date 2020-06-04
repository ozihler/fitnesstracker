import {NodeVisitor} from './node.visitor';
import {TreeNode} from './tree-node';

export class DisableAllNodesVisitor implements NodeVisitor {
  visit(node: TreeNode) {
    if (node) {
      node.disable();
    }

    if (node.children) {
      node.children.forEach(child => this.visit(child));
    }
  }

}

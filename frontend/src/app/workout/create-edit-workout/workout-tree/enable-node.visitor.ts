import {TreeNode} from './tree-node';
import {NodeByNameCollectorVisitor} from './node-by-name-collector.visitor';
import {NodeVisitor} from './node.visitor';

export class EnableNodeVisitor implements NodeVisitor {
  private readonly nodeCollector: NodeByNameCollectorVisitor;

  private constructor(nodeId: string, private _treeNode: TreeNode) {
    this.nodeCollector = new NodeByNameCollectorVisitor(nodeId);
  }

  get treeNode(): TreeNode {
    return this._treeNode;
  }

  static find(nodeId: string, treeNode: TreeNode): EnableNodeVisitor {
    return new EnableNodeVisitor(nodeId, treeNode);
  }

  private static enableIfNeeded(node: TreeNode) {
    if (!node.isEnabled) {
      node.enable();
    }
  }

  enableNodes(): void {
    this.treeNode.accept(this.nodeCollector);
    const foundNode = this.nodeCollector.foundNode;

    if (foundNode) {
      foundNode.accept(this);
    }
  }

  visit(node: TreeNode) {
    if (!node) {
      return;
    }

    if (node.parent) {
      this.visit(node.parent);
    }

    EnableNodeVisitor.enableIfNeeded(node);

    if (node.children) {
      node.children.forEach(child => EnableNodeVisitor.enableIfNeeded(child));
    }
  }
}

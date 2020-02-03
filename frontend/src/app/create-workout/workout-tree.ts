import {Workout} from "../shared/workout";
import {TreeNode} from "../shared/tree-node";

export class WorkoutTree {
  constructor(private _root: Workout) {

  }

  get root(): Workout {
    return this._root;
  }

  // todo extract tree to move convenience methods there
  private static linkNodes(child: TreeNode, parent: TreeNode) {
    child.parent = parent;
    parent.children.push(child);
  }

  addNode(selectedElement: TreeNode) {
    let foundNode = this.find(this._root, selectedElement.parent);

    if (foundNode) {
      WorkoutTree.linkNodes(selectedElement, foundNode);
    }
  }

  disableAllNodesOf(parent: TreeNode) {
    parent.isEnabled = false;
    if (parent.children) {
      parent.children.forEach(node => this.disableAllNodesOf(node));
    }
  }

  enable(node: TreeNode) {
    if (!node) {
      return;
    }
    node.isEnabled = true;
    this.enable(node.parent);
  }

  private find(node: TreeNode, elementToFind: TreeNode): TreeNode {
    if (!elementToFind) {
      return node;
    }

    if (node.name === elementToFind.name) {
      return node;
    } else {
      return this.findNodeInChildren(elementToFind, node.children);
    }
  }

  private findNodeInChildren(elementToFind: TreeNode, children: TreeNode[]) {
    if (!children) {
      return undefined;
    }
    for (const child of children) {
      let foundNode = this.find(child, elementToFind);

      if (foundNode) {
        return foundNode;
      }
    }
  }
}

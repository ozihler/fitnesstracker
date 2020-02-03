import {Workout} from "../shared/workout";
import {TreeNode} from "../shared/tree-node";

export class WorkoutTree {
  constructor(private _root: Workout) {

  }

  get root(): Workout {
    return this._root;
  }

  static enable(node: TreeNode): void {
    if (!node) {
      return;
    }
    node.isEnabled = true;
    this.enable(node.parent);
  }

  // todo extract tree to move convenience methods there
  private static linkNodes(child: TreeNode, parent: TreeNode) {
    child.parent = parent;
    parent.children.push(child);
  }

  disableAllNodesOf(parent: TreeNode) {
    parent.isEnabled = false;
    if (parent.children) {
      parent.children.forEach(node => this.disableAllNodesOf(node));
    }
  }

  static findNodeInChildren(elementToFind: TreeNode, nodesToSearch: TreeNode[]) {
    if (!nodesToSearch) {
      return undefined;
    }

    for (const child of nodesToSearch) {
      let foundNode = WorkoutTree.find(elementToFind, child);

      if (foundNode) {
        return foundNode;
      }
    }
  }

  static find(elementToFind: TreeNode, nodeToSearch: TreeNode): TreeNode {
    if (!elementToFind || nodeToSearch.name === elementToFind.name) {
      return nodeToSearch;
    }

    return WorkoutTree.findNodeInChildren(elementToFind, nodeToSearch.children);
  }

  private static findByName(nodeToSearch: TreeNode, name: string) {
    if (!nodeToSearch) {
      return undefined;
    }

    if (nodeToSearch.name === name) {
      return nodeToSearch;
    }

    if (!nodeToSearch.children) {
      return undefined;
    }

    for (const child of nodeToSearch.children) {
      let node = WorkoutTree.findByName(child, name);
      if (node) {
        return node;
      }
    }

    return undefined;
  }

  addNode(nodeToAdd: TreeNode) {
    let foundNode = WorkoutTree.find(nodeToAdd.parent, this.root);

    if (foundNode) {
      WorkoutTree.linkNodes(nodeToAdd, foundNode);
    }
  }

  findNodeByName(nodeName: string) {
    return WorkoutTree.findByName(this.root, nodeName);
  }
}

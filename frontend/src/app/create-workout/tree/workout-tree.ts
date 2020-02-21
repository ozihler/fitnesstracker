import {Workout} from "../../shared/workout";
import {TreeNode} from "./tree-node";
import {EnableNodeVisitor} from "./enable-node.visitor";
import {DisableAllNodesVisitor} from "./disable-all-nodes.visitor";
import {NodeByNameCollectorVisitor} from "./node-by-name-collector.visitor";
import {Type} from "../../shared/type";

export class WorkoutTree {
  constructor(private _root: Workout) {
    this.select(_root.name);
  }

  private _currentSelection: TreeNode;

  get currentSelection(): TreeNode {
    return this._currentSelection;
  }

  set currentSelection(value: TreeNode) {
    this._currentSelection = value;
  }

  get childrenOfCurrentSelection(): TreeNode[] {
    return this.currentSelection && this.currentSelection.children ? this.currentSelection.children : [];
  }

  get root(): Workout {
    return this._root;
  }

  set root(value: Workout) {
    this._root = value;
  }

  get someChildrenAreEnabled() {
    return this.currentSelection.children && this.currentSelection.children.some(value => value.isEnabled);
  }

  enable(nodeName: string): void {
    if (!nodeName) {
      return;
    }

    this.disableAllNodes();

    let enableNodeVisitor = EnableNodeVisitor.find(nodeName, this.root);

    enableNodeVisitor.enableNodes();
  }

  disableAllNodes(): void {
    this.root.accept(new DisableAllNodesVisitor())
  }

  // Todo this should not have a parameter of type tree node but only names and a type --> should create a node and return it
  addNode(nodeToAdd: TreeNode): boolean {
    if (nodeToAdd.type === Type.Muscle_Group) {
      return this.link(nodeToAdd, this.root);
    }
    let foundParentOfNode = this.findNodeByName(nodeToAdd.parent.name);
    if (foundParentOfNode) {
      return this.link(nodeToAdd, foundParentOfNode);
    } else {
      return false;
    }
  }

  findNodeByName(nodeName: string): TreeNode {
    let nodeByNameCollectorVisitor = new NodeByNameCollectorVisitor(nodeName);
    this.root.accept(nodeByNameCollectorVisitor);
    return nodeByNameCollectorVisitor.foundNode;
  }

  select(nodeName: string) {
    this.currentSelection = this.findNodeByName(nodeName);

    if (this.currentSelection.isEnabled && this.someChildrenAreEnabled) {
      this.disableChildrenOf(this.currentSelection);
    } else {
      this.enable(this.currentSelection.name);
    }
  }

  private link(nodeToAdd: TreeNode, parent: TreeNode) {
    nodeToAdd.linkToParent(parent);
    this.enable(nodeToAdd.name);
    return true;
  }

  private disableChildrenOf(currentSelection: TreeNode) {
    currentSelection.children.forEach(child => child.accept(new DisableAllNodesVisitor()));
  }

  delete(nodeName: string) {
    let treeNode = this.findNodeByName(nodeName);

    this.removeFromParent(treeNode);

    if (this.hasSiblings(treeNode)) {
      this.currentSelection = treeNode.parent.children[0];
    } else {
      if (treeNode.parent) {
        this.currentSelection = treeNode.parent;
      }
    }
  }

  private isSet(treeNode: TreeNode) {
    return treeNode.type === Type.Set;
  }

  private hasSiblings(treeNode: TreeNode) {
    return treeNode.parent.children.length > 0;
  }

  private removeFromParent(treeNode: TreeNode) {
    let count = 0;
    console.error("# children before: " + treeNode.parent.children.length);
    let treeNodeIndex = treeNode.parent.children.indexOf(treeNode);
    console.error("Tree node index: " + treeNodeIndex)
    if (treeNodeIndex >= 0) {
      treeNode.parent.children.splice(treeNodeIndex, 1);
    }
    console.error("# children after delete: " + treeNode.parent.children.length);
  }
}

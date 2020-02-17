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
    return this.currentSelection.children.some(value => value.isEnabled);
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

  delete(nodeToDelete: TreeNode) {
    let treeNode = this.findNodeByName(nodeToDelete.name);
    treeNode.parent.children = treeNode.parent.children.filter(c => c.name != treeNode.name);
    if (this.hasSiblings(treeNode)) {
      this.enableFirstSibling(treeNode);
    }
  }

  private enableFirstSibling(treeNode: TreeNode) {
    return treeNode.parent.children[0].enable();
  }

  private hasSiblings(treeNode: TreeNode) {
    return treeNode.parent.children && treeNode.parent.children.length > 0;
  }
}

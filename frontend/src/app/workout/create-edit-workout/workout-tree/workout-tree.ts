import {Workout} from '../../shared/workout';
import {TreeNode} from './tree-node';
import {EnableNodeVisitor} from './enable-node.visitor';
import {DisableAllNodesVisitor} from './disable-all-nodes.visitor';
import {NodeByNameCollectorVisitor} from './node-by-name-collector.visitor';
import {Type} from '../../shared/type';

export class WorkoutTree {
  constructor(private _root: Workout) {
    this.select(_root.id);
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

  enable(nodeId: string): void {
    if (!nodeId) {
      return;
    }

    this.disableAllNodes();

    const enableNodeVisitor = EnableNodeVisitor.find(nodeId, this.root);

    enableNodeVisitor.enableNodes();
  }

  disableAllNodes(): void {
    this.root.accept(new DisableAllNodesVisitor());
  }

  // Todo this should not have a parameter of type tree
  //  node but only names and a type --> should create a node and return it
  addNode(nodeToAdd: TreeNode): boolean {
    if (nodeToAdd.typeOfCurrentlySelection === Type.Muscle_Group) {
      return this.link(nodeToAdd, this.root);
    }
    const foundParentOfNode = this.findNodeById(nodeToAdd.parent.id);
    if (foundParentOfNode) {
      return this.link(nodeToAdd, foundParentOfNode);
    } else {
      return false;
    }
  }

  findNodeById(nodeId: string): TreeNode {
    const nodeByNameCollectorVisitor = new NodeByNameCollectorVisitor(nodeId);
    this.root.accept(nodeByNameCollectorVisitor);
    return nodeByNameCollectorVisitor.foundNode;
  }

  select(nodeId: string) {
    this.currentSelection = this.findNodeById(nodeId);
    if (this.currentSelection.isEnabled && this.someChildrenAreEnabled) {
      this.disableChildrenOf(this.currentSelection);
    } else {
      this.enable(this.currentSelection.id);
    }
  }

  delete(nodeId: string) {
    const treeNode = this.findNodeById(nodeId);
    WorkoutTree.removeFromParent(treeNode);

    if (treeNode.parent) {
      this.currentSelection = treeNode.parent;
    }
  }

  private disableChildrenOf(currentSelection: TreeNode) {
    currentSelection.children.forEach(child => child.accept(new DisableAllNodesVisitor()));
  }

  private link(nodeToAdd: TreeNode, parent: TreeNode) {
    nodeToAdd.linkToParent(parent);
    this.enable(nodeToAdd.id);
    return true;
  }

  private static isSet(treeNode: TreeNode) {
    return treeNode.typeOfCurrentlySelection === Type.Set;
  }

  private static hasSiblings(treeNode: TreeNode) {
    return treeNode.parent.children.length > 0;
  }

  private static removeFromParent(treeNode: TreeNode) {
    const count = 0;
    const treeNodeIndex = treeNode.parent.children.indexOf(treeNode);
    if (treeNodeIndex >= 0) {
      treeNode.parent.children.splice(treeNodeIndex, 1);
    }
  }

  setCurrentSelectionAsParentOf(nodes: TreeNode[]) {
    nodes.forEach(node => node.parent = this.currentSelection);
  }

  public getCumulativeWeight(): number {
    throw new Error('Not implemented yet!');
  }
}

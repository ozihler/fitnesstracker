import {Workout} from '../../shared/workout';
import {TreeNode} from './tree-node';
import {EnableNodeVisitor} from './enable-node.visitor';
import {DisableAllNodesVisitor} from './disable-all-nodes.visitor';
import {NodeByNameCollectorVisitor} from './node-by-name-collector.visitor';
import {Type} from '../../shared/type';
import {Set} from '../../shared/set';
import {Exercise} from '../../shared/exercise';

export class WorkoutTree {
  constructor(private _root: Workout) {
    this.select(_root.id);
  }

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

  private _currentSelection: TreeNode;

  private static isSet(treeNode: TreeNode) {
    return treeNode.type === Type.Set;
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

  addNode(nodeToAdd: TreeNode): boolean {
    if (nodeToAdd.type === Type.Muscle_Group) {
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

  private addExerciseMultiplierToSet(assumedExerciseParent: TreeNode, assumedSet: TreeNode) {
    if (assumedExerciseParent.type === Type.Exercise && assumedSet.type === Type.Set) {
      (assumedSet as Set).multiplier = (assumedExerciseParent as Exercise).multiplier;
    }
  }

  setCurrentSelectionAsParentOf(nodes: TreeNode[]) {
    nodes.forEach(node => node.parent = this.currentSelection);
  }

  public getCumulativeWeight(): number {
    throw new Error('Not implemented yet!');
  }
}

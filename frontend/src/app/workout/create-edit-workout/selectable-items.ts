import {TreeNode} from './workout-tree/tree-node';
import {Type} from '../shared/type';

export class SelectableItems {
  selectableItems: TreeNode[] = [];

  add(node: TreeNode) {
    if (!this.isSelectableItem(node) || this.contains(node)) {
      return;
    }

    this.selectableItems.push(node);
  }

  private isSelectableItem(nodeToDelete: TreeNode) {
    return [Type.Muscle_Group, Type.Exercise].indexOf(nodeToDelete.type) >= 0;
  }

  remove(node: TreeNode) {
    if (!this.isSelectableItem(node)) {
      return;
    }
    this.selectableItems = this.selectableItems.filter(s => s.id !== node.id);
  }

  contains(node: TreeNode) {
    return this.selectableItems.map(s => s.name).indexOf(node.id) >= 0;
  }

  set items(selectableItems: TreeNode[]) {
    this.selectableItems = selectableItems;
  }

  get items() {
    return this.selectableItems;
  }
}

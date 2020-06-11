import {TreeNode} from "./workout-tree/tree-node";

export class SelectableItems {
  selectableItems: TreeNode[] = [];

  add(node: TreeNode) {
    if (this.contains(node)) {
      return; // it's a set...
    }

    this.selectableItems.push(node)
  }

  remove(node: TreeNode) {
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

import {TreeNode} from '../create-edit-workout/workout-tree/tree-node';
import {Type} from './type';

export class ElementsToId {
  static replace(input: string) {
    if (!input) {
      return 'root';
    }
    return input.replace(/#+/g, '')
      .replace(/[\s\,\.]+/g, '-')
      .toLowerCase();
  }

  static replaceTreeNode(input: TreeNode) {
    if (!input || !input.name || Type.Workout === input.type) {
      return 'root';
    }
    return input.name.replace(/#+/g, '')
      .replace(/[\s\,\.]+/g, '-')
      .toLowerCase();
  }
}

import {TreeNode} from './tree-node';

export interface NodeVisitor {
  visit(node: TreeNode);
}

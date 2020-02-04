import {Component, OnInit} from '@angular/core';
import {WorkoutService} from "../shared/workout.service";
import {TreeNode} from "./tree/tree-node";
import {Type} from "../shared/type";
import {Set} from "../shared/set"
import {WorkoutTree} from "./tree/workout-tree";

@Component({
  selector: 'app-create-workout',
  template: `
    <div>{{workoutTree?.root?.creationDate}} {{workoutTree?.root?.title}}</div>
    <hr/>
    <app-button-tree
      [node]="workoutTree?.root"
      [currentSelectionName]="workoutTree?.currentSelection?.name"
      (changeSelectionEvent)="changeTreeNode($event)">
    </app-button-tree>
    <hr/>
    <app-element-selection
      [currentSelection]="workoutTree?.currentSelection"
      [selectableElements]="selectableChildrenOfSelectedNode"
      (addNodeEvent)="addSelectedItemToTree($event)"
      (createsChildEvent)="createChildInSelection($event)">
    </app-element-selection>
    <hr/>
  `
})
export class CreateWorkoutComponent implements OnInit {
  workoutTree: WorkoutTree;
  selectableChildrenOfSelectedNode: TreeNode[] = [];

  constructor(private workoutService: WorkoutService) {
  }

  ngOnInit() {
    this.workoutService.newWorkout()
      .subscribe(workout => {
        this.workoutTree = new WorkoutTree(workout);
        this.fetchMuscleGroupsAndFilterOut([]);
      }, error => console.log(error));
  }

  changeTreeNode(node: TreeNode) {
    this.workoutTree.select(node.name);

    if (node.type === Type.Workout) {
      this.fetchMuscleGroupsAndFilterOut(this.workoutTree.childrenOfCurrentSelection.map(m => m.name));
    } else if (node.type === Type.Muscle_Group) {
      this.fetchExercisesForAndFilterOut(node.name, this.workoutTree.childrenOfCurrentSelection.map(m => m.name));
    }
  }

  addSelectedItemToTree(selectedElement: TreeNode) {
    let addedNode = this.workoutTree.addNode(selectedElement);

    if (addedNode) {
      this.selectableChildrenOfSelectedNode = this.selectableChildrenOfSelectedNode.filter(s => s.name !== selectedElement.name);
    }
  }

  createChildInSelection(elements: string) {
    if (this.workoutTree.currentSelection.type === Type.Workout) {
      this.workoutService.newMuscleGroup(elements)
        .subscribe(createdMuscleGroups => this.updateSelectableNodesNodes(createdMuscleGroups));
    } else if (this.workoutTree.currentSelection.type === Type.Muscle_Group) {
      this.workoutService.newExercises(this.workoutTree.currentSelection, elements)
        .subscribe(createdExercises => this.updateSelectableNodesNodes(createdExercises));
    } else if (this.workoutTree.currentSelection.type === Type.Exercise) {
      this.workoutService.newSetInExercise(this.workoutTree.currentSelection, elements)
        .subscribe(createdSet => this.addSetToExercise([createdSet]));
    }
  }

  private fetchExercisesForAndFilterOut(name: string, children: string[] = []) {
    this.workoutService.fetchExercisesFor(name)
      .subscribe(exercises => this.updateSelectableElements(exercises, children));
  }

  private fetchMuscleGroupsAndFilterOut(selectedChildren: string[] = []) {
    this.workoutService.fetchMuscleGroups()
      .subscribe(muscleGroups => this.updateSelectableElements(muscleGroups, selectedChildren));
  }

  private updateSelectableNodesNodes(nodes: TreeNode[]) {
    nodes.forEach(node => this.selectableChildrenOfSelectedNode.push(node));
  }

  private updateSelectableElements(nodes: TreeNode[], selectedChildren: string[]) {
    return this.selectableChildrenOfSelectedNode = nodes.filter(node => selectedChildren.indexOf(node.name) < 0);
  }

  private addSetToExercise(sets: Set[]) {
    sets.forEach(set => this.workoutTree.addNode(set));
  }
}

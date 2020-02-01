import {Component, OnInit} from '@angular/core';
import {WorkoutService} from "../shared/workout.service";
import {Workout} from '../shared/workout';
import {TreeNode} from "../shared/tree-node";
import {Type} from "../shared/type";

@Component({
  selector: 'app-create-workout',
  template: `
    <div>{{workout?.creationDate}} {{workout?.title}}</div>
    =============================
    <app-button-tree
      [node]="workout"
      (changeSelectionEvent)="changeSelectionThroughTreeClick($event)">
    </app-button-tree>
    =============================
    <app-element-selection
      [type]="currentSelection?.type + 1"
      [selectableElements]="selectableChildrenOfSelectedNode"
      (addNodeEvent)="addNode($event)"
      (createsChildEvent)="createChild($event)">
    </app-element-selection>
    =============================
  `
})
export class CreateWorkoutComponent implements OnInit {
  selectableChildrenOfSelectedNode: TreeNode[] = [];
    workout: Workout;
    currentSelection: TreeNode;

  constructor(private workoutService: WorkoutService) {
  }

  ngOnInit() {
    this.workoutService.newWorkout()
      .subscribe(workout => {
        this.workout = workout;
        this.currentSelection = workout;
      }, error => console.log(error));


    this.fetchMuscleGroupsAndFilterOut();

  }

  private fetchMuscleGroupsAndFilterOut(children: string[] = []) {
    this.workoutService.fetchMuscleGroups()
      .subscribe(muscleGroups => this.updateSelectableElements(muscleGroups, children));
  }


  changeSelectionThroughTreeClick(node: TreeNode) {
    this.currentSelection = node;
    if (node.type === Type.Workout) {
      this.fetchMuscleGroupsAndFilterOut(node.children.map(m => m.name));
    } else if (node.type === Type.Muscle_Group) {
      this.fetchExercisesForAndFilterOut(node.name, node.children.map(m => m.name));
    }
  }


  addNode(selectedElement: TreeNode) {
    let foundNode = this.findSelectedElement(this.workout, selectedElement.parent);

    if (foundNode) {
      this.linkNodes(selectedElement, foundNode);

      this.selectableChildrenOfSelectedNode = this.selectableChildrenOfSelectedNode.filter(s => s.name !== selectedElement.name);
      this.disableAllNodesOf(this.workout);
      this.enable(selectedElement);
    }
  }

  // todo extract tree to move convenience methods there
  private linkNodes(child: TreeNode, parent: TreeNode) {
    child.parent = parent;
    parent.children.push(child);
  }

  private fetchExercisesForAndFilterOut(name: string, children: string[] = []) {
    this.workoutService.fetchExercisesFor(name)
      .subscribe(exercises => this.updateSelectableElements(exercises, children));
  }

  createChild(elements: string) {
    if (this.currentSelection.type === Type.Workout) {
      this.workoutService.newMuscleGroup(elements)
        .subscribe(createdMuscleGroups => this.updateSelectedNodes(createdMuscleGroups));
    } else if (this.currentSelection.type === Type.Muscle_Group) {
      this.workoutService.newExercises(this.currentSelection, elements)
        .subscribe(createdExercises => this.updateSelectedNodes(createdExercises));
    } else if (this.currentSelection.type === Type.Exercise) {
      this.workoutService.newSetInExercise(this.currentSelection, elements)
        .subscribe(createdSet => this.updateSelectedNodes(createdSet));
    }

  }

  private updateSelectedNodes(nodes: TreeNode[]) {
    nodes.forEach(node => this.selectableChildrenOfSelectedNode.push(node));
  }

  private updateSelectableElements(nodes: TreeNode[], children: string[]) {
    return this.selectableChildrenOfSelectedNode = nodes.filter(node => children.indexOf(node.name) < 0);
  }

  private disableAllNodesOf(parent: TreeNode) {
    parent.isEnabled = false;
    if (parent.children) {
      parent.children.forEach(node => this.disableAllNodesOf(node));
    }
  }

  private enable(node: TreeNode) {
    if (!node) {
      return;
    }
    node.isEnabled = true;
    this.enable(node.parent);
  }

  private findSelectedElement(node: TreeNode, selectedElement: TreeNode): TreeNode {
    if (!selectedElement) {
      return node;
    }

    if (node.name === selectedElement.name) {
      return node;
    } else {

      if (node.children) {
        for (const child of node.children) {
          let foundNode = this.findSelectedElement(child, selectedElement);

          if (foundNode) {
            return foundNode;
          }
        }
      }

      return undefined;
    }
  }
}

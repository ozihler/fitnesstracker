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
    <app-button-tree [node]="workout"
                     (changeSelectionEvent)="changeSelectionThroughTreeClick($event)">
    </app-button-tree>
    =============================
    <app-element-selection
      [type]="currentSelection?.type"
      [selectableElements]="selectableNodes"
      (selectedElement)="selectElement($event)"
      (createsChildEvent)="createChild($event)">
    </app-element-selection>
    =============================
  `
})
export class CreateWorkoutComponent implements OnInit {
  selectableNodes: TreeNode[] = [];
  private workout: Workout;
  private currentSelection: TreeNode;

  constructor(private workoutService: WorkoutService) {
  }

  ngOnInit() {
    this.workoutService.newWorkout()
      .subscribe(workout => {
        this.workout = workout;
        this.currentSelection = workout;
      });

    this.fetchMuscleGroupsAndFilterOut();

  }

  private fetchMuscleGroupsAndFilterOut(children: string[] = []) {
    this.workoutService.fetchMuscleGroups()
      .subscribe(muscleGroups => this.updateSelectableElements(muscleGroups, children));
  }


  private fetchExercisesForAndFilterOut(name: string, children: string[] = []) {
    this.workoutService.fetchExercisesFor(name)
      .subscribe(exercises => this.updateSelectableElements(exercises, children));
  }

  private updateSelectableElements(nodes: TreeNode[], children: string[]) {
    return this.selectableNodes = nodes.filter(node => children.indexOf(node.name) < 0);
  }

  changeSelectionThroughTreeClick(node: TreeNode) {
    this.currentSelection = node;
    if (node.type === Type.Workout) {
      this.fetchMuscleGroupsAndFilterOut(node.children.map(m => m.name));
    } else if (node.type === Type.Muscle_Group) {
      this.fetchExercisesForAndFilterOut(node.name, node.children.map(m => m.name));
    }
  }

  createChild(elements: string) {
    if (this.currentSelection.type === Type.Workout) {
      this.workoutService.newMuscleGroup(elements)
        .subscribe(createdMuscleGroups => this.updateSelectedNodes(createdMuscleGroups));
    } else if (this.currentSelection.type === Type.Muscle_Group) {
      this.workoutService.newExercises(this.currentSelection, elements)
        .subscribe(createdExercises => this.updateSelectedNodes(createdExercises));
    }

  }

  private updateSelectedNodes(nodes: TreeNode[]) {
    nodes.forEach(node => this.selectableNodes.push(node));
  }

  selectElement(selectedElement: TreeNode) {
    let foundNode = this.findSelectedElement(this.workout, selectedElement.parent);

    console.log("Found node: ", foundNode)
    if (foundNode) {
      foundNode.children.push(selectedElement);
      this.selectableNodes = this.selectableNodes.filter(s => s.name !== selectedElement.name);
    }
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

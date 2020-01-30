import {Component, OnInit} from '@angular/core';
import {WorkoutService} from "../shared/workout.service";
import {Workout} from '../shared/workout';
import {TreeNode} from "../shared/tree-node";
import {Type} from "../shared/type";
import {MuscleGroup} from "../shared/muscle-group";
import {Exercise} from "../shared/exercise";

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
      [selectableElements]="selectableElements"
      (selectedElement)="selectElement($event)"
      (createsChildEvent)="createChild($event)">
    </app-element-selection>
    =============================
  `
})
export class CreateWorkoutComponent implements OnInit {
  private workout: Workout;
  selectableElements: TreeNode[] = [];
  private currentSelection: TreeNode;

  constructor(private workoutService: WorkoutService) {
  }

  ngOnInit() {
    this.workoutService.newWorkout()
      .subscribe(workout => {
        this.workout = workout;
        this.currentSelection = workout;
        console.log("Current Selection", this.currentSelection);
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
    return this.selectableElements = nodes.filter(node => children.indexOf(node.name) < 0);
  }

  changeSelectionThroughTreeClick(node: TreeNode) {
    if (node.type === Type.Workout) {
      this.fetchMuscleGroupsAndFilterOut(node.children.map(m=>m.name));
    } else if (node.type === Type.Muscle_Group) {
      this.fetchExercisesForAndFilterOut(node.name, node.children.map(m=>m.name));
    }
    this.currentSelection = node;
  }

  createChild(elements: string) {
    if (this.currentSelection.type == Type.Muscle_Group) {
      this.workoutService.newExercises(this.currentSelection, elements)
        .subscribe(createdExercises => createdExercises.forEach(exercise => this.selectableElements.push(exercise)));
    }

  }

  selectElement(selectedElement: TreeNode) {
    let foundNode = this.findSelectedElement(this.workout, selectedElement.parent);

    console.log("Found node: ",foundNode)
    if (foundNode) {
      foundNode.children.push(selectedElement);
      this.selectableElements = this.selectableElements.filter(s => s.name !== selectedElement.name);
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

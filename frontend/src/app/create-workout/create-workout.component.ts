import {Component, OnInit} from '@angular/core';
import {WorkoutService} from "../shared/workout.service";
import {TreeNode} from "./tree/tree-node";
import {Type} from "../shared/type";
import {Set} from "../shared/set"
import {WorkoutTree} from "./tree/workout-tree";
import {ActivatedRoute} from "@angular/router";
import {SelectionService} from "../shared/selection.service";

@Component({
  selector: 'app-create-workout',
  template: `
    <a routerLink="/workouts-overview">Back to Overview</a>
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
      (createsChildEvent)="createSelectableElement($event)"
      (deleteNodeEvent)="deleteSelectableElement($event)">
    </app-element-selection>
    <hr/>
  `
})
export class CreateWorkoutComponent implements OnInit {
  workoutTree: WorkoutTree;
  selectableChildrenOfSelectedNode: TreeNode[] = [];

  constructor(private workoutService: WorkoutService,
              private selectionService: SelectionService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      let workoutGid = params.get("workout-gid");
      if (workoutGid) {
        this.workoutService.fetchWorkoutWithId(workoutGid)
          .subscribe(
            workout => {
              this.workoutTree = new WorkoutTree(workout);
              this.fetchMuscleGroupsAndFilterOut(this.workoutTree.childrenOfCurrentSelection.map(c => c.name));
            },
            error => console.log(error));
      } else {
        this.workoutService.newWorkout()
          .subscribe(
            workout => {
              this.workoutTree = new WorkoutTree(workout);
              this.fetchMuscleGroupsAndFilterOut(this.workoutTree.childrenOfCurrentSelection.map(c => c.name));
            },
            error => console.log(error));
      }
    });
  }

  changeTreeNode(node: TreeNode) {
    this.workoutTree.select(node.name);
    this.fetchChildrenOf(node);
  }

  private fetchChildrenOf(node: TreeNode) {
    if (node.type === Type.Workout) {
      this.fetchMuscleGroupsAndFilterOut(this.workoutTree.childrenOfCurrentSelection.map(m => m.name));
    } else if (node.type === Type.Muscle_Group) {
      this.fetchExercisesForAndFilterOut(node.name, this.workoutTree.childrenOfCurrentSelection.map(m => m.name));
    }
  }

  private removeFromSelectableElements(selectedElement: TreeNode) {
    this.selectableChildrenOfSelectedNode = this.selectableChildrenOfSelectedNode.filter(s => s.name !== selectedElement.name);
  }

  private static isSelectableElement(selectedElement: TreeNode) {
    return selectedElement.type !== Type.Set;
  }

  createSelectableElement(elements: string) {
    if (this.workoutTree.currentSelection.type === Type.Workout) {
      this.selectionService.newMuscleGroup(elements)
        .subscribe(createdMuscleGroups => this.updateSelectableNodes(createdMuscleGroups));
    } else if (this.workoutTree.currentSelection.type === Type.Muscle_Group) {
      this.selectionService.createExercises(this.workoutTree.currentSelection, elements)
        .subscribe(createdExercises => this.updateSelectableNodes(createdExercises));
    } else if (this.workoutTree.currentSelection.type === Type.Exercise) {
      this.selectionService.addSetToExerciseExercise(this.workoutTree.root.gid, this.workoutTree.currentSelection, elements)
        .subscribe(createdSet => this.addSetToExercise([createdSet]));
    }
  }

  addSelectedItemToTree(selectedElement: TreeNode) {
    let addedNode = this.workoutTree.addNode(selectedElement);

    if (addedNode) {
      if (CreateWorkoutComponent.isSelectableElement(selectedElement)) {
        this.removeFromSelectableElements(selectedElement);
      }

      this.workoutService.updateWorkout(this.workoutTree.root)
        .subscribe(workout => {
          this.workoutTree.root = workout;
          this.workoutTree.enable(selectedElement.name);
        });
    }
  }

  private fetchExercisesForAndFilterOut(name: string, children: string[] = []) {
    this.selectionService.fetchExercisesFor(name)
      .subscribe(exercises => {
        this.updateParents(exercises, this.workoutTree.currentSelection);
        this.updateSelectableElements(exercises, children)
      });
  }

  private fetchMuscleGroupsAndFilterOut(selectedChildren: string[] = []) {
    this.selectionService.createMuscleGroups()
      .subscribe(muscleGroups => this.updateSelectableElements(muscleGroups, selectedChildren));
  }

  private updateSelectableNodes(nodes: TreeNode[]) {
    this.updateParents(nodes, this.workoutTree.currentSelection);
    nodes.forEach(node => this.selectableChildrenOfSelectedNode.push(node));
  }

  private updateSelectableElements(nodes: TreeNode[], selectedChildren: string[]) {
    return this.selectableChildrenOfSelectedNode = nodes.filter(node => selectedChildren.indexOf(node.name) < 0);
  }

  private addSetToExercise(sets: Set[]) {
    this.updateParents(sets, this.workoutTree.currentSelection);
    sets.forEach(set => this.addSelectedItemToTree(set));
  }

  private updateParents(nodes: TreeNode[], parent: TreeNode) {
    nodes.forEach(node => node.parent = parent);
  }

  deleteSelectableElement(element: TreeNode) {

    //todo make sure workouts that used a musclegroup/exercise can still be loaded: e.g., only add a flag "used" or so
    if (element.type == Type.Muscle_Group) {
      this.selectionService.deleteMuscleGroup(element.name)
        .subscribe(() => this.fetchChildrenOf(this.workoutTree.currentSelection)/*just reload the whole thing*/);
    } else if (element.type == Type.Exercise) {
      this.selectionService.deleteExercise(element.name)
        .subscribe(deletedExercise => this.fetchChildrenOf(this.workoutTree.currentSelection));
    }
  }
}

import {Component, OnInit} from '@angular/core';
import {WorkoutService} from "../shared/services/workout.service";
import {TreeNode} from "./workout-tree/tree-node";
import {Type} from "../shared/type";
import {Set} from "../shared/set"
import {WorkoutTree} from "./workout-tree/workout-tree";
import {ActivatedRoute} from "@angular/router";
import {SelectionService} from "../shared/services/selection.service";
import {WorkoutTitleUpdate} from "./title/workout-title-update";

@Component({
  selector: 'app-create-workout',
  template: `
    <app-workout-title
      [workoutId]="workoutTree?.root?.workoutId?.value"
      [workoutTitle]="workoutTree?.root?.title"
      [workoutCreationDate]="workoutTree?.root?.creationDate"
      (changeTitleEvent)="updateWorkoutTitle($event)">
    </app-workout-title>
    <hr class="uk-divider-icon"/>
    <workout-tree
      [node]="workoutTree?.root"
      [currentSelectionName]="workoutTree?.currentSelection?.name"
      (changeSelectionEvent)="changeTreeNode($event)"
      (removeFromWorkoutEvent)="removeNodeFromWorkout($event)">
    </workout-tree>
    <hr class="uk-divider-icon"/>
    <muscle-group-or-exercise-selection
      [currentSelection]="workoutTree?.currentSelection"
      [selectableElements]="selectableChildrenOfSelectedNode"
      (addNodeEvent)="addSelectedItemToTree($event)"
      (createsChildEvent)="createSelectableElement($event)"
      (deleteNodeEvent)="deleteSelectableElement($event)">
    </muscle-group-or-exercise-selection>
    <hr class="uk-divider-icon"/>
    <a routerLink="/workout/overview">Back to Overview</a>
  `
})
export class CreateWorkoutComponent implements OnInit {
  // todo rename to "edit Workout component" or similar, see angular book
  workoutTree: WorkoutTree;
  selectableChildrenOfSelectedNode: TreeNode[] = [];

  constructor(private workoutService: WorkoutService,
              private selectionService: SelectionService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      let workoutId = params.get("workoutId");
      this.workoutService.createNewOrFetchWorkoutWithId(workoutId)
        .subscribe(workout => {
            this.workoutTree = new WorkoutTree(workout);
            this.fetchMuscleGroupsAndFilterOut(this.workoutTree.childrenOfCurrentSelection.map(c => c.name));
          },
          error => console.log(error));
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
    const {type} = this.workoutTree.currentSelection;

    if (type === Type.Workout) {
      this.selectionService.newMuscleGroup(elements)
        .subscribe(createdMuscleGroups => {
          this.updateSelectableNodes(createdMuscleGroups)
          this.workoutTree.root.title
        });
    } else if (Type.Muscle_Group === type) {
      this.selectionService.createExercises(this.workoutTree.currentSelection, elements)
        .subscribe(createdExercises => this.updateSelectableNodes(createdExercises));
    } else if (Type.Exercise === type) {
      this.selectionService.addSetToExerciseExercise(this.workoutTree.root.workoutId, this.workoutTree.currentSelection, elements)
        .subscribe(createdSet => this.addSetToExercise([createdSet]));
    }
  }

  // todo do enable selected Item (or not, test whats better and make a list)
  addSelectedItemToTree(selectedElement: TreeNode) {
    let addedNode = this.workoutTree.addNode(selectedElement);

    if (addedNode) {
      if (CreateWorkoutComponent.isSelectableElement(selectedElement)) {
        this.removeFromSelectableElements(selectedElement);
      }

      this.updateWorkoutAndEnable(selectedElement);
    }
  }

  private updateWorkoutAndEnable(selectedElement: TreeNode) {
    this.updateWorkoutTitleIfMuscleGroup(selectedElement);
    this.workoutService.updateWorkout(this.workoutTree.root)
      .subscribe(workout => {
        this.workoutTree.root = workout;
        this.workoutTree.enable(selectedElement.name);
      });
  }

  private updateWorkoutTitleIfMuscleGroup(selectedElement: TreeNode) {
    if (selectedElement.type == Type.Muscle_Group) {
      if (this.isInitialWorkoutTitle()) {
        this.setMuscleGroupAsWorkoutTitle(selectedElement);
      } else if (this.isMuscleGroupNotYetAppendedToTitle(selectedElement)) {
        this.appendMuscleGroupToTitle(selectedElement);
      }
    }
  }

  private isInitialWorkoutTitle() {
    return this.workoutTree.root.title === WorkoutService.WORKOUT_INITIAL_TITLE;
  }

  private setMuscleGroupAsWorkoutTitle(selectedElement: TreeNode) {
    this.workoutTree.root.title = selectedElement.name;
  }

  private isMuscleGroupNotYetAppendedToTitle(selectedElement: TreeNode) {
    return this.workoutTree.root.title.indexOf(selectedElement.name) < 0;
  }

  private appendMuscleGroupToTitle(selectedElement: TreeNode) {
    this.workoutTree.root.title += " " + selectedElement.name;
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

    nodes.forEach(node => {
      if (!this.contains(node)) {
        this.selectableChildrenOfSelectedNode.push(node)
      }
    });
  }

  private contains(node: TreeNode) {
    return this.selectableChildrenOfSelectedNode.map(s => s.name).indexOf(node.name) >= 0;
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
    if (element.type == Type.Muscle_Group) {
      this.selectionService.deleteMuscleGroup(element.name)
        .subscribe(() => this.fetchChildrenOf(this.workoutTree.currentSelection)/*just reload the whole thing*/);
    } else if (element.type == Type.Exercise) {
      this.selectionService.deleteExercise(element.name)
        .subscribe(() => this.fetchChildrenOf(this.workoutTree.currentSelection));
    }
  }

  private deleteMuscleGroupFromTitle(element: TreeNode) {
    this.workoutTree.root.title = this.workoutTree.root.title.replace(element.name, ``);
    if (this.workoutTree.root.title.trim().length === 0) {
      this.workoutTree.root.title = WorkoutService.WORKOUT_INITIAL_TITLE;
    }
  }

  updateWorkoutTitle(updatedWorkout: WorkoutTitleUpdate) {
    let selectedElement = this.workoutTree.currentSelection;
    this.workoutTree.root.name = updatedWorkout.workoutTitle;
    this.workoutTree.root.creationDate = updatedWorkout.workoutCreationDate;
    this.updateWorkoutAndEnable(selectedElement);
  }

  removeNodeFromWorkout(nodeToDelete: TreeNode) {
    console.log("DELETED ELEMENT: ",nodeToDelete)
    this.deleteMuscleGroupFromTitle(nodeToDelete);
    this.workoutTree.delete(nodeToDelete.name);
    this.fetchChildrenOf(this.workoutTree.currentSelection);
  }
}

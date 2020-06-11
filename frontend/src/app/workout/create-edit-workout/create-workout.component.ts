import {Component, OnInit} from '@angular/core';
import {WorkoutService} from '../shared/services/workout.service';
import {TreeNode} from './workout-tree/tree-node';
import {Type} from '../shared/type';
import {Set} from '../shared/set';
import {WorkoutTree} from './workout-tree/workout-tree';
import {ActivatedRoute} from '@angular/router';
import {SelectionService} from '../shared/services/selection.service';
import {WorkoutHeaderUpdate} from './title/workout-header-update';
import {Exercise} from '../shared/exercise';
import {Workout} from '../shared/workout';
import {SelectableItems} from "./selectable-items";
import set = Reflect.set;

@Component({
  selector: 'app-create-workout',
  template: `
    <app-workout-header
      [workoutId]="workoutTree?.root?.workoutId?.value"
      [workoutTitle]="workoutTree?.root?.name"
      [workoutCreationDate]="workoutTree?.root?.creationDate"
      (changeHeaderEvent)="updateWorkoutHeader($event)">
    </app-workout-header>
    <hr class="uk-divider-icon"/>
    <app-workout-tree
      [node]="workoutTree?.root"
      [currentSelectionId]="workoutTree?.root?.id"
      (changeSelectionEvent)="changeSelectedWorkoutTreeNode($event)"
      (removeFromWorkoutEvent)="removeNodeFromWorkout($event)">
    </app-workout-tree>
    <hr class="uk-divider-icon"/>
    <app-selectable-items
      [currentSelection]="workoutTree?.currentSelection"
      [selectableItems]="selectableItems.items"
      (addNodeEvent)="addSelectedItemToTree($event)"
      (createsChildEvent)="createSelectableElement($event)"
      (deleteNodeEvent)="deleteSelectableElement($event)">
    </app-selectable-items>
    <hr class="uk-divider-icon"/>
    <a routerLink="/overview">Back to Overview</a>
  `
})
export class CreateWorkoutComponent implements OnInit {
  // todo rename to "edit Workout component" or similar, see angular book
  workoutTree: WorkoutTree;
  selectableItems: SelectableItems = new SelectableItems();

  constructor(private workoutService: WorkoutService,
              private selectionService: SelectionService,
              private route: ActivatedRoute) {
  }

  private static isSelectableItem(nodeToDelete: TreeNode) {
    return [Type.Muscle_Group, Type.Exercise].indexOf(nodeToDelete.typeOfCurrentlySelection) >= 0;
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const workoutId = params.get('workoutId');
      this.workoutService.createNewOrFetchWorkoutWithId(workoutId)
        .subscribe(workout => {
            this.workoutTree = new WorkoutTree(workout);
            this.fetchMuscleGroupsAndFilterOut(this.workoutTree.childrenOfCurrentSelection.map(c => c.name));
          },
          error => console.error(error));
    });
  }

  changeSelectedWorkoutTreeNode(node: TreeNode) {
    console.log("Select ", node.id);
    this.workoutTree.select(node.id);
    this.fetchSelectableItemsOf(node);
  }

  private fetchSelectableItemsOf(node: TreeNode) {
    if (Type.Workout === node.typeOfCurrentlySelection) {
      this.fetchMuscleGroupsAndFilterOut(this.workoutTree.childrenOfCurrentSelection.map(m => m.name));
    } else if (Type.Muscle_Group === node.typeOfCurrentlySelection) {
      this.fetchExercisesForAndFilterOut(node.name, this.workoutTree.childrenOfCurrentSelection.map(m => m.name));
    }
  }

  removeNodeFromWorkout(nodeToDelete: TreeNode) {
    this.deleteMuscleGroupFromTitleIfItIsAMuscleGroup(nodeToDelete);
    this.workoutTree.delete(nodeToDelete.id);
    if (CreateWorkoutComponent.isSelectableItem(nodeToDelete)) {
      this.selectableItems.add(nodeToDelete);
    }
  }

  // todo instead of string, use Exercise and MuscleGroup! (define union type of MuscleGroup | Exercise | Set)
  createSelectableElement(elements: string | Set) {
    const {typeOfCurrentlySelection} = this.workoutTree.currentSelection;

    if (Type.Workout === typeOfCurrentlySelection) {
      this.createMuscleGroups(elements as string);
    } else if (Type.Muscle_Group === typeOfCurrentlySelection) {
      this.createExercises(elements as string);
    } else if (Type.Exercise === typeOfCurrentlySelection) {
      this.addSetToExercise(elements as Set);
    }
  }

  private addSetToExercise(elements: Set) {
    this.selectionService.addSetToExerciseExercise(
      this.workoutTree.root.workoutId,
      this.workoutTree.currentSelection as Exercise,
      elements as Set)
      .subscribe(createdSet => this.addToExercise([createdSet]));
  }

  private createExercises(elements: string) {
    this.selectionService.createExercises(
      this.workoutTree.currentSelection,
      elements as string)
      .subscribe(createdExercises => this.updateSelectableNodes(createdExercises));
  }

  private createMuscleGroups(elements: string) {
    this.selectionService.newMuscleGroup(elements)
      .subscribe(createdMuscleGroups => this.updateSelectableNodes(createdMuscleGroups));
  }

// todo do enable selected Item (or not, test whats better and make a list)
  addSelectedItemToTree(selectedElement: TreeNode) {
    console.log("Added node ", selectedElement.name)
    const addedNode = this.workoutTree.addNode(selectedElement);

    if (addedNode) {
      if (CreateWorkoutComponent.isSelectableItem(selectedElement)) {
        this.selectableItems.remove(selectedElement);
      }

      this.updateWorkoutAndEnable(selectedElement);
    }
  }

  private updateWorkoutAndEnable(selectedElement: TreeNode) {
    this.updateWorkoutTitleAfterSelectingMuscleGroup(selectedElement);
    this.workoutService.updateWorkout(this.workoutTree.root)
      .subscribe(workout => {
        this.workoutTree.root = workout;
        this.workoutTree.enable(selectedElement.id);
      });
  }

  deleteSelectableElement(element: TreeNode) {
    if (element.typeOfCurrentlySelection === Type.Muscle_Group) {
      this.selectionService.deleteMuscleGroup(element.name)
        .subscribe(() => this.fetchSelectableItemsOf(this.workoutTree.currentSelection)/*just reload the whole thing*/);
    } else if (element.typeOfCurrentlySelection === Type.Exercise) {
      this.selectionService.deleteExercise(element.name)
        .subscribe(() => this.fetchSelectableItemsOf(this.workoutTree.currentSelection));
    }
  }

  private isInitialWorkoutTitle() {
    console.log(this.workoutTree.root.name);
    return this.workoutTree.root.name === Workout.WORKOUT_INITIAL_TITLE;
  }

  private setMuscleGroupAsWorkoutTitle(selectedElement: TreeNode) {
    this.workoutTree.root.name = selectedElement.name;
  }

  private isMuscleGroupNotYetAppendedToTitle(selectedElement: TreeNode) {
    return this.workoutTree.root.name.indexOf(selectedElement.name) < 0;
  }

  updateWorkoutHeader(updatedWorkout: WorkoutHeaderUpdate) {
    this.workoutTree.root.creationDate = updatedWorkout.workoutCreationDate;
    this.updateWorkoutAndEnable(this.workoutTree.currentSelection);
  }

  private updateWorkoutTitleAfterSelectingMuscleGroup(selectedElement: TreeNode) {
    if (Type.Muscle_Group !== selectedElement.typeOfCurrentlySelection) {
      return;
    }

    if (this.isInitialWorkoutTitle()) {
      this.setMuscleGroupAsWorkoutTitle(selectedElement);
    } else if (this.isMuscleGroupNotYetAppendedToTitle(selectedElement)) {
      this.appendMuscleGroupToTitle(selectedElement);
    }
  }

  private fetchMuscleGroupsAndFilterOut(selectedChildren: string[] = []) {
    this.selectionService.getMuscleGroups()
      .subscribe(muscleGroups => this.selectableItems.items = (muscleGroups.filter(node => selectedChildren.indexOf(node.name) < 0)));
  }

  private appendMuscleGroupToTitle(selectedElement: TreeNode) {
    this.workoutTree.root.name += ' ' + selectedElement.name;
  }

  private addToExercise(sets: Set[]) {
    this.workoutTree.setCurrentSelectionAsParentOf(sets);
    sets.forEach(set => this.addSelectedItemToTree(set));
  }

  private fetchExercisesForAndFilterOut(name: string, children: string[] = []) {
    this.selectionService.fetchExercisesFor(name)
      .subscribe(exercises => {
        this.workoutTree.setCurrentSelectionAsParentOf(exercises);
        this.selectableItems.items = (exercises.filter(node => children.indexOf(node.name) < 0));
      });
  }

  private deleteMuscleGroupFromTitleIfItIsAMuscleGroup(element: TreeNode) {
    this.workoutTree.root.name = this.workoutTree.root.name.replace(element.name, ``).trim();
    if (this.workoutTree.root.name.trim().length === 0) {
      this.workoutTree.root.name = Workout.WORKOUT_INITIAL_TITLE;
    }
  }

  private updateSelectableNodes(nodes: TreeNode[]) {
    this.workoutTree.setCurrentSelectionAsParentOf(nodes);
    nodes.forEach(node => this.selectableItems.add(node));
  }
}

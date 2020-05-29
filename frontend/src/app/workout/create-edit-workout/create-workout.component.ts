import {Component, OnInit} from '@angular/core';
import {WorkoutService} from '../shared/services/workout.service';
import {TreeNode} from './workout-tree/tree-node';
import {Type} from '../shared/type';
import {Set} from '../shared/set';
import {WorkoutTree} from './workout-tree/workout-tree';
import {ActivatedRoute} from '@angular/router';
import {SelectionService} from '../shared/services/selection.service';
import {WorkoutTitleUpdate} from './title/workout-title-update';
import {Exercise} from '../shared/exercise';
import {Workout} from '../shared/workout';

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
    <app-workout-tree
      [node]="workoutTree?.root"
      [currentSelectionName]="workoutTree?.currentSelection?.name"
      (changeSelectionEvent)="changeTreeNode($event)"
      (removeFromWorkoutEvent)="removeNodeFromWorkout($event)">
    </app-workout-tree>
    <hr class="uk-divider-icon"/>
    <app-muscle-group-or-exercise-selection
      [currentSelection]="workoutTree?.currentSelection"
      [selectableElements]="selectableChildrenOfSelectedWorkoutTreeNode"
      (addNodeEvent)="addSelectedItemToTree($event)"
      (createsChildEvent)="createSelectableElement($event)"
      (deleteNodeEvent)="deleteSelectableElement($event)">
    </app-muscle-group-or-exercise-selection>
    <hr class="uk-divider-icon"/>
    <a routerLink="/workout/overview">Back to Overview</a>
  `
})
export class CreateWorkoutComponent implements OnInit {

  constructor(private workoutService: WorkoutService,
              private selectionService: SelectionService,
              private route: ActivatedRoute) {
  }

  // todo rename to "edit Workout component" or similar, see angular book
  workoutTree: WorkoutTree;
  selectableChildrenOfSelectedWorkoutTreeNode: TreeNode[] = [];

  private static isSelectableElement(selectedElement: TreeNode) {
    return selectedElement.type !== Type.Set;
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const workoutId = params.get('workoutId');
      this.workoutService.createNewOrFetchWorkoutWithId(workoutId)
        .subscribe(workout => {
            console.log('Workout: ', workout);
            this.workoutTree = new WorkoutTree(workout);
            this.fetchMuscleGroupsAndFilterOut(this.workoutTree.childrenOfCurrentSelection.map(c => c.name));
          },
          error => console.error(error));
    });
  }

  changeTreeNode(node: TreeNode) {
    this.workoutTree.select(node.id);
    this.fetchChildrenOf(node);
  }

  private fetchChildrenOf(node: TreeNode) {
    if (Type.Workout === node.type) {
      this.fetchMuscleGroupsAndFilterOut(this.workoutTree.childrenOfCurrentSelection.map(m => m.name));
    } else if (Type.Muscle_Group === node.type) {
      this.fetchExercisesForAndFilterOut(node.name, this.workoutTree.childrenOfCurrentSelection.map(m => m.name));
    }
  }

  removeNodeFromWorkout(nodeToDelete: TreeNode) {
    this.deleteMuscleGroupFromTitleIfItIsAMuscleGroup(nodeToDelete);
    this.workoutTree.delete(nodeToDelete.id);
    this.selectableChildrenOfSelectedWorkoutTreeNode.push(nodeToDelete);
  }

  createSelectableElement(elements: string) {
    const {type} = this.workoutTree.currentSelection;

    if (type === Type.Workout) {
      this.selectionService.newMuscleGroup(elements)
        .subscribe(createdMuscleGroups => {
          this.updateSelectableNodes(createdMuscleGroups);
        });
    } else if (Type.Muscle_Group === type) {
      console.log('Current selection: ', this.workoutTree.currentSelection);
      this.selectionService.createExercises(this.workoutTree.currentSelection, elements)
        .subscribe(createdExercises => this.updateSelectableNodes(createdExercises));
    } else if (Type.Exercise === type) {
      this.selectionService.addSetToExerciseExercise(
        this.workoutTree.root.workoutId,
        this.workoutTree.currentSelection as Exercise, elements)
        .subscribe(createdSet => this.addSetToExercise([createdSet]));
    }
  }

  // todo do enable selected Item (or not, test whats better and make a list)
  addSelectedItemToTree(selectedElement: TreeNode) {
    const addedNode = this.workoutTree.addNode(selectedElement);

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
        this.workoutTree.enable(selectedElement.id);
      });
  }

  deleteSelectableElement(element: TreeNode) {
    if (element.type === Type.Muscle_Group) {
      this.selectionService.deleteMuscleGroup(element.name)
        .subscribe(() => this.fetchChildrenOf(this.workoutTree.currentSelection)/*just reload the whole thing*/);
    } else if (element.type === Type.Exercise) {
      this.selectionService.deleteExercise(element.name)
        .subscribe(() => this.fetchChildrenOf(this.workoutTree.currentSelection));
    }
  }

  private isInitialWorkoutTitle() {
    return this.workoutTree.root.name === Workout.WORKOUT_INITIAL_TITLE;
  }

  private setMuscleGroupAsWorkoutTitle(selectedElement: TreeNode) {
    this.workoutTree.root.name = Workout.WORKOUT_PREFIX + ' ' + selectedElement.name;
  }

  private isMuscleGroupNotYetAppendedToTitle(selectedElement: TreeNode) {
    return this.workoutTree.root.name.indexOf(selectedElement.name) < 0;
  }

  updateWorkoutTitle(updatedWorkout: WorkoutTitleUpdate) {
    const selectedElement = this.workoutTree.currentSelection;
    this.workoutTree.root.name = updatedWorkout.workoutTitle;
    this.workoutTree.root.creationDate = updatedWorkout.workoutCreationDate;
    this.updateWorkoutAndEnable(selectedElement);
  }

  private updateWorkoutTitleIfMuscleGroup(selectedElement: TreeNode) {
    if (selectedElement.type === Type.Muscle_Group) {
      if (this.isInitialWorkoutTitle()) {
        this.setMuscleGroupAsWorkoutTitle(selectedElement);
      } else if (this.isMuscleGroupNotYetAppendedToTitle(selectedElement)) {
        this.appendMuscleGroupToTitle(selectedElement);
      }
    }
  }

  private fetchMuscleGroupsAndFilterOut(selectedChildren: string[] = []) {
    this.selectionService.getMuscleGroups()
      .subscribe(muscleGroups => this.updateSelectableElements(muscleGroups, selectedChildren));
  }

  private appendMuscleGroupToTitle(selectedElement: TreeNode) {
    this.workoutTree.root.name += ' ' + selectedElement.name;
  }

  private removeFromSelectableElements(selectedElement: TreeNode) {
    this.selectableChildrenOfSelectedWorkoutTreeNode
      = this.selectableChildrenOfSelectedWorkoutTreeNode
      .filter(s => s.name !== selectedElement.name);
  }

  private contains(node: TreeNode) {
    return this.selectableChildrenOfSelectedWorkoutTreeNode.map(s => s.name).indexOf(node.name) >= 0;
  }

  private addSetToExercise(sets: Set[]) {
    this.updateParents(sets, this.workoutTree.currentSelection);
    sets.forEach(set => this.addSelectedItemToTree(set));
  }

  private updateParents(nodes: TreeNode[], parent: TreeNode) {
    nodes.forEach(node => node.parent = parent);
  }

  private fetchExercisesForAndFilterOut(name: string, children: string[] = []) {
    this.selectionService.fetchExercisesFor(name)
      .subscribe(exercises => {
        this.updateParents(exercises, this.workoutTree.currentSelection);
        this.updateSelectableElements(exercises, children);
      });
  }

  private deleteMuscleGroupFromTitleIfItIsAMuscleGroup(element: TreeNode) {
    this.workoutTree.root.name = this.workoutTree.root.name.replace(element.name, ``).trim();
    if (this.workoutTree.root.name.replace(Workout.WORKOUT_PREFIX, '').trim().length === 0) {
      this.workoutTree.root.name = Workout.WORKOUT_INITIAL_TITLE;
    }
  }

  private updateSelectableElements(nodes: TreeNode[], selectedChildren: string[]) {
    return this.selectableChildrenOfSelectedWorkoutTreeNode = nodes.filter(node => selectedChildren.indexOf(node.name) < 0);
  }

  private updateSelectableNodes(nodes: TreeNode[]) {
    this.updateParents(nodes, this.workoutTree.currentSelection);

    nodes.forEach(node => {
      if (!this.contains(node)) {
        this.selectableChildrenOfSelectedWorkoutTreeNode.push(node);
      }
    });
  }
}

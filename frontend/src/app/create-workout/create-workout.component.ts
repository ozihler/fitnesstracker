import {Component, OnInit} from '@angular/core';
import {WorkoutService} from "../shared/workout.service";
import {ButtonNode} from "../workout-details-view/button-group/button-node";
import {Workout} from '../shared/workout';
import {SelectableElement} from "../shared/selectable-element";
import {SelectableElementFactory} from "../shared/selectable-element-factory";
import {SubtreeFactory} from "../shared/subtree.factory";
import {MuscleGroupFactory} from "../shared/muscle-group.factory";
import {Type} from "../shared/type";

@Component({
  selector: 'app-create-workout',
  template: `
    <div>{{workout?.creationDate}} {{workout?.title}}</div>
    =============================
    <app-button-tree [node]="workoutTree"
                     (changeSelectionEvent)="changeSelectionInTree($event)">
    </app-button-tree>
    =============================
    <app-element-selection
      [selectableElements]="selectableElements"
      [type]="currentType"
      (selectedElement)="selectElement($event)"
      (createsElementEvent)="createElements($event)">
    </app-element-selection>
    =============================
  `,
  styles: []
})
export class CreateWorkoutComponent implements OnInit {
  workoutTree: ButtonNode;
  selectableElements: SelectableElement[] = [];
  currentType: Type = Type.Muscle_Group;
  private workout: Workout;
  private currentSelection: SelectableElement;
  private parentOfSelectedElement: SelectableElement;


  constructor(private workoutService: WorkoutService) {
  }

  ngOnInit() {
    this.workoutService.newWorkout()
      .subscribe(workout => {
        this.workout = workout;
        this.workoutTree = SubtreeFactory.fromWorkout(workout);
      });

    this.fetchMuscleGroupsAndFilterOut();

  }

  private fetchMuscleGroupsAndFilterOut(filterMuscleGroups: string[] = []) {
    this.workoutService.fetchMuscleGroups()
      .subscribe(muscleGroups => {
        this.selectableElements = muscleGroups.map(mG => SelectableElementFactory.from(mG.name, Type.Muscle_Group))
          .filter(m => filterMuscleGroups && filterMuscleGroups.indexOf(m.name) < 0);
        this.currentType = Type.Muscle_Group;
      });
  }

  selectElement(selection: SelectableElement) {
    if (selection.type === Type.Muscle_Group) {
      this.workout.muscleGroups.push(MuscleGroupFactory.fromName(selection.name));
      this.workoutTree = SubtreeFactory.fromWorkout(this.workout);
      this.selectableElements = this.selectableElements.filter(m => m.name !== selection.name);
      this.workoutService.update(this.workout);
    } else if (selection.type == Type.Exercise) {
      console.log("Chose Exercise");
    }

  }


  changeSelectionInTree(node: ButtonNode) {
    console.log("Selected ", node);
    if (node.type === Type.Workout) {
      this.fetchMuscleGroupsAndFilterOut(node.children.map(c => c.name));
    } else if (node.type === Type.Muscle_Group) {
      this.workoutService.fetchExercisesFor(node.name)
        .subscribe(exercises => {
          this.selectableElements = exercises.map(mG => SelectableElementFactory.from(mG.name, Type.Exercise));
          this.currentType = Type.Exercise;
          this.parentOfSelectedElement = this.currentSelection;
        });
    } else if (node.type === Type.Exercise) {
      this.workoutService.fetchSetsForExercise(this.workout.gid, node.parent, node.name);
    }
  }

  createElements(elements: string) {
    console.log("Current Selection:", this.currentSelection, this.currentType)
    if (this.currentType === Type.Muscle_Group && !this.currentSelection) {
      this.workoutService.newMuscleGroup(elements)
        .subscribe(createdMuscleGroups => {
          createdMuscleGroups.forEach(element => {
            this.selectableElements.push({name: element.name, type: this.currentType})
          });
        });
    } else if (this.currentType === Type.Exercise) {
      this.workoutService.newExercises(this.parentOfSelectedElement.name, elements)
        .subscribe(elements => {
          elements.forEach(element => this.selectableElements.push({name: element.name, type: this.currentType}));
        });
    }
  }
}


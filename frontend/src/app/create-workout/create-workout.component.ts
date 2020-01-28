import {Component, OnInit} from '@angular/core';
import {WorkoutService} from "../shared/workout.service";
import {ButtonNode, Type} from "../workout-details-view/button-group/button-node";
import {Workout} from '../shared/workout';
import {SelectableElement} from "../shared/selectable-element";
import {SelectableElementFactory} from "../shared/selectable-element-factory";
import {SubtreeFactory} from "../shared/subtree.factory";
import {MuscleGroupFactory} from "../shared/muscle-group.factory";

@Component({
  selector: 'app-create-workout',
  template: `
    <div>{{workout?.creationDate}} {{workout?.title}}</div>
    =============================
    <app-button-tree [node]="workoutTree"
                     (changeSelectionEvent)="changeSelection($event)">
    </app-button-tree>
    =============================
    <app-element-selection
      [selectableElements]="selectableElements"
      (selectedElement)="selectElement($event)">
    </app-element-selection>
    =============================
  `,
  styles: []
})
export class CreateWorkoutComponent implements OnInit {
  workoutTree: ButtonNode;
  selectableElements: SelectableElement[] = [];
  private workout: Workout;


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
      });
  }

  selectElement(selection: SelectableElement) {
    if (selection.type === Type.Muscle_Group) {
      this.workout.muscleGroups.push(MuscleGroupFactory.fromName(selection.name));
      this.workoutTree = SubtreeFactory.fromWorkout(this.workout);
      this.selectableElements = this.selectableElements.filter(m => m.name !== selection.name);
      this.workoutService.update(this.workout);
    }

  }


  changeSelection(node: ButtonNode) {
    console.log("Selected ", node);
    if (node.type === Type.Workout) {
      this.fetchMuscleGroupsAndFilterOut(node.children.map(c => c.name));
    }else if (node.type === Type.Muscle_Group) {
      this.workoutService.fetchExercisesFor(node.name);
    }
  }
}


import {ComponentFixture, ComponentFixtureAutoDetect, fakeAsync, TestBed} from '@angular/core/testing';
import {CreateWorkoutComponent} from '../../../app/workout/create-edit-workout/create-workout.component';
import {LOCALE_ID, NO_ERRORS_SCHEMA} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {WorkoutService} from '../../../app/workout/shared/services/workout.service';
import {SelectionService} from '../../../app/workout/shared/services/selection.service';
import {of} from 'rxjs';
import {Workout} from '../../../app/workout/shared/workout';
import {WorkoutId} from '../../../app/workout/shared/workout-id';
import {MuscleGroup} from '../../../app/workout/shared/muscle-group';
import {WorkoutTitleComponent} from '../../../app/workout/create-edit-workout/title/workout-title.component';
import {WorkoutTreeComponent} from '../../../app/workout/create-edit-workout/selection/workout-tree.component';
import {MuscleGroupOrExerciseSelectionComponent} from '../../../app/workout/create-edit-workout/administration/muscle-groups-and-exercises/muscle-group-or-exercise-selection.component';
import {DatePipe, registerLocaleData} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {ReplacePipe} from '../../../app/workout/shared/pipes/replace.pipe';
import localeDe from '@angular/common/locales/de';

fdescribe('Create Workout', () => {

  let component: CreateWorkoutComponent;
  let fixture: ComponentFixture<CreateWorkoutComponent>;

  class MockRoute {
    public paramMap = of({
      get(x: string) {
        return undefined;
      }
    });
  }

  const workoutServiceMock = {
      createNewOrFetchWorkoutWithId(workoutId: string) {
        return of(new Workout(
          WorkoutId.from('1234'),
          new Date(),
          Workout.WORKOUT_INITIAL_TITLE,
          undefined));
      }
    }
  ;
  const selectionServiceMock = {
    getMuscleGroups: () => of([
      new MuscleGroup(undefined, 'Chest', []),
      new MuscleGroup(undefined, 'Triceps', []),
      new MuscleGroup(undefined, 'Biceps', []),
      new MuscleGroup(undefined, 'Back', [])
    ])
  };

  beforeEach(() => {
    registerLocaleData(localeDe);
    TestBed.configureTestingModule({
      declarations: [
        CreateWorkoutComponent,
        WorkoutTitleComponent,
        WorkoutTreeComponent,
        MuscleGroupOrExerciseSelectionComponent,
        ReplacePipe,
        DatePipe

      ],
      providers: [
        {provide: ActivatedRoute, useClass: MockRoute},
        {provide: WorkoutService, useValue: workoutServiceMock},
        {provide: SelectionService, useValue: selectionServiceMock},
        {provide: ComponentFixtureAutoDetect, useValue: true},
        {provide: LOCALE_ID, useValue: localeDe},
        {provide: DatePipe},
        {provide: ReplacePipe},
      ],
      imports: [
        ReactiveFormsModule
      ],
      schemas: [NO_ERRORS_SCHEMA]
    })
      .compileComponents();

    fixture = TestBed.createComponent(CreateWorkoutComponent);
    component = fixture.componentInstance;

  });

  it('should work', fakeAsync(() => {
    fixture.detectChanges();
    expect(fixture.nativeElement.querySelector('#edit-workout-title-button').textContent).toContain(Workout.WORKOUT_PREFIX + ' New Workout');
  }));
});


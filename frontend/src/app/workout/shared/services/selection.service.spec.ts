import {SelectionService} from "./selection.service";
import {of} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {MuscleGroup} from "../muscle-group";
import {TestBed, inject} from "@angular/core/testing";

describe(`SelectionService`, () => {
  const expectedMuscleGroups = {
    muscleGroups: [
      {
        name: 'Chest',
        exercises: [
          {
            name: 'Bench Press'
          },
          {
            name: 'Dumbbell Flys'
          }
        ]
      }
    ]
  };

  let httpStub;

  beforeEach(() => {
    httpStub = {
      get: () => of(expectedMuscleGroups)
    };

    TestBed.configureTestingModule({
      providers: [
        {
          provide: HttpClient,
          useValue: httpStub
        },
        SelectionService
      ]
    })
  });

  it("should fetch all muscle groups",
    inject([SelectionService],
      (service: SelectionService) => {
        let receivedMuscleGroups: MuscleGroup[] = [];

        service.getMuscleGroups().subscribe(
          m => receivedMuscleGroups = m
        );

        expect(receivedMuscleGroups.length).toBe(1);
        expect(receivedMuscleGroups[0].name).toEqual(expectedMuscleGroups.muscleGroups[0].name);
        expect(receivedMuscleGroups[0].children.length).toEqual(expectedMuscleGroups.muscleGroups[0].exercises.length);
      })
  );


});



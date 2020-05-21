import {Button} from '../page_elements/button.pe';
import {by, element} from 'protractor';

export class WorkoutsOverview {
  private createNewWorkoutButton: Button;

  constructor() {
    this.createNewWorkoutButton = new Button('create-new-workout-button');
  }

  clickNewWorkoutButton() {
    return this.createNewWorkoutButton.click();
  }

  deleteAllWorkouts() {
    return element.all(by.css('.ft-delete-workout-button'))
      .then(buttons => buttons.forEach(button => button.click()));
  }
}

import {Button} from './page_elements/button.pe';
import {browser} from 'protractor';

export class FitnessTypeSelection {
  private selectWorkoutButton: Button;

  constructor() {
    this.selectWorkoutButton = new Button('select-workout-button');
  }

  enter() {
    //browser.get('http://localhost:5000/api/admin/purge');
    return browser.get(browser.baseUrl) as Promise<any>;
  }

  clickSelectWorkoutButton() {
    return this.selectWorkoutButton.click();
  }

}

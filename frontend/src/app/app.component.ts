import {Component} from '@angular/core';
import {environment} from "../environments/environment";

@Component({
  selector: 'app-root',
  template: `
    <div>
      <span>Base Url: {{baseUrl}}</span>
      <h1>{{title}}</h1>
      <router-outlet></router-outlet>
    </div>`
})
export class AppComponent {
  title: string = 'Fitness Tracker';
  baseUrl: string = environment.baseUrl;
}


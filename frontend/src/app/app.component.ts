import {Component, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";
import {pipe} from "rxjs";
import {map} from "rxjs/operators";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-root',
  template: `
    <div class="uk-text-center">
      <router-outlet></router-outlet>

    </div>`
})
export class AppComponent {

}


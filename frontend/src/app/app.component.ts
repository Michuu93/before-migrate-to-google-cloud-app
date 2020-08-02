import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <body class="mat-app-background">
    <mat-card class="app-content">
      <app-form></app-form>
    </mat-card>
    </body>
  `,
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend-app';
}

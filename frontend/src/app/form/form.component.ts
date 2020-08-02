import {Component} from '@angular/core';
import {FormService} from "./form.service";
import {Data} from "./data";

@Component({
  selector: 'app-form',
  template: `
    <mat-card class="app-content">
      <form class="form">
        <mat-form-field floatLabel="always">
          <input matInput placeholder="manufacturer" name="manufacturer" [(ngModel)]="data.manufacturer">
        </mat-form-field>
        <mat-form-field floatLabel="always">
          <input matInput placeholder="model" name="model" [(ngModel)]="data.model">
        </mat-form-field>
      </form>
      <div>Data to send:
        <pre>{{ data | json }}</pre>
      </div>
      <div *ngIf="saveResponse">Response:
        <pre>{{ saveResponse | json }}</pre>
      </div>
      <div class="rightButton">
        <button (click)="sendData()" color="primary" mat-raised-button>Send</button>
      </div>
    </mat-card>
    <mat-card class="app-content">
      <form class="form">
        <mat-form-field floatLabel="always">
          <input matInput placeholder="uuid" name="uuid" [(ngModel)]="uuid" class="uuid">
        </mat-form-field>
      </form>
      <div *ngIf="findByUuidResponse">Response:
        <pre>{{ findByUuidResponse | json }}</pre>
      </div>
      <div class="rightButton">
        <button *ngIf="saveResponse && saveResponse['id']" (click)="copyUuid()" color="secondary" mat-raised-button>Copy
          UUID
        </button>
        <button (click)="findByUuid()" color="primary" mat-raised-button>Find</button>
      </div>
    </mat-card>
    <mat-card class="app-content">
      <div *ngIf="findAllResponse">Response:
        <pre>{{ findAllResponse | json }}</pre>
      </div>
      <div class="rightButton">
        <button (click)="findAll()" color="primary" mat-raised-button>Find All</button>
      </div>
    </mat-card>
    <mat-card class="app-content">
      <div *ngIf="deleteAllResponse">Response:
        <pre>{{ deleteAllResponse | json }}</pre>
      </div>
      <div class="centerButton">
        <button (click)="deleteAll()" color="warn" mat-raised-button>Delete All</button>
      </div>
    </mat-card>
  `,
  styleUrls: ['./form.component.scss']
})
export class FormComponent {
  data: Data = {manufacturer: '', model: ''};
  uuid: string;
  saveResponse: Object;
  findByUuidResponse: Object;
  findAllResponse: Object;
  deleteAllResponse: Object;

  constructor(private formService: FormService) {
  }

  sendData(): void {
    this.formService.upsertData(this.data).subscribe(response => this.saveResponse = response, error => this.saveResponse = error.error);
  }

  findByUuid(): void {
    this.formService.findByUuid(this.uuid).subscribe(response => this.findByUuidResponse = response, error => this.findByUuidResponse = error.error);
  }

  copyUuid(): void {
    this.uuid = this.saveResponse['id'];
  }

  findAll(): void {
    this.formService.findAll().subscribe(response => this.findAllResponse = response, error => this.findAllResponse = error.error);
  }

  deleteAll(): void {
    this.formService.deleteAll().subscribe(() => this.deleteAllResponse = 'Deleted', error => this.deleteAllResponse = error.error);
  }
}

import {ChangeDetectionStrategy, Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-form',
  template: `
    <ng-container>
      <form class="form">
        <mat-form-field floatLabel="always">
          <input matInput placeholder="manufacturer" name="manufacturer"
                 [(ngModel)]="data.manufacturer">
        </mat-form-field>
        <mat-form-field floatLabel="always">
          <input matInput placeholder="model" name="model"
                 [(ngModel)]="data.model">
        </mat-form-field>
      </form>
      <div>Data to send:
        <pre>{{ data | json }}</pre>
      </div>
      <!--      <div *ngIf="pubSubResponse">Response:-->
      <!--        <pre>{{ pubSubResponse }}</pre>-->
      <!--      </div>-->
      <div class="rightButton">
        <button (click)="sendData()" color="primary" mat-raised-button>Send</button>
      </div>
    </ng-container>
  `,
  styleUrls: ['./form.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class FormComponent {
  data: { manufacturer: string, model: string } = {manufacturer: '', model: ''};

  constructor(private httpClient: HttpClient) {
  }

  sendData() {
    console.debug(`Sending ${JSON.stringify(this.data)}`);
  }
}

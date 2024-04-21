import { Component, OnInit, ViewChild, inject } from '@angular/core';
import { DocService } from '../../../core/shared/services/doc/doc.service';
import { ActivatedRoute, Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { DocResponse } from 'src/app/core/interfaces/doc.interface';

import {
  EventSettingsModel,
  ScheduleComponent,
  View,
} from '@syncfusion/ej2-angular-schedule';

@Component({
  selector: 'app-doc-schedule',
  templateUrl: './doc-schedule.component.html',
  styleUrls: ['./doc-schedule.component.scss'],
})
export class DocScheduleComponent implements OnInit {
  docService = inject(DocService);
  activatedRoute = inject(ActivatedRoute);
  router = inject(Router);

  public doctor?: DocResponse;

  @ViewChild('scheduleObj') public scheduleObj?: ScheduleComponent;

  public selectedDate: Date;

  public currView: View = 'WorkWeek';

  public eventSettings: EventSettingsModel = {
    dataSource: [
      {
        Id: 20,
        Subject: 'Booking',
        StartTime: new Date(),
        EndTime: new Date(),
        CategoryColor: '#ea7a57',
        IsBlock: true,
        IsAllDay: true,
      },
    ],
  };

  constructor() {
    this.selectedDate = new Date();
  }

  ngOnInit(): void {
    sessionStorage.removeItem('fecha');
    this.getDocInfo();
  }

  getDocInfo() {
    this.activatedRoute.params
      .pipe(switchMap(({ id }) => this.docService.getDocById(id)))
      .subscribe({
        next: (resp) => {
          this.doctor = resp;
          console.log(resp);
        },
      });
  }

  public readonly: boolean = false;

  onDateSelect(eventArgs: any) {
    this.selectedDate = eventArgs.data.StartTime;

    sessionStorage.setItem('fecha', this.selectedDate.toString());

    this.router.navigate(['/appointment-form'], {
      state: { doctorData: this.doctor },
    });
  }
}

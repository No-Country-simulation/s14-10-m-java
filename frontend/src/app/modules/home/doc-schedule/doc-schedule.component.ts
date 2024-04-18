import { Component, OnInit, ViewChild, inject } from '@angular/core';
import { DocService } from '../../../core/shared/services/doc/doc.service';
import { ActivatedRoute, Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { DocResponse } from 'src/app/core/interfaces/doc.interface';

import { ScheduleComponent } from '@syncfusion/ej2-angular-schedule';

@Component({
  selector: 'app-doc-schedule',
  templateUrl: './doc-schedule.component.html',
  styleUrls: ['./doc-schedule.component.scss'],
})
export class DocScheduleComponent implements OnInit {
  doctor?: DocResponse;

  docService = inject(DocService);
  activatedRoute = inject(ActivatedRoute);
  router = inject(Router);

  @ViewChild('scheduleObj') public scheduleObj?: ScheduleComponent;

  public selectedDate: Date;

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
    console.log('Fecha seleccionada:', this.selectedDate);
    console.log(this.selectedDate.toISOString());
    sessionStorage.setItem('fecha', this.selectedDate.toISOString());

    this.router.navigate(['/appointment-confirmation'], {
      state: { doctorData: this.doctor },
    });
  }
}

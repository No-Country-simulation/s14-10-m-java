import { Component, OnInit, inject } from '@angular/core';
import { DocService } from '../../../core/shared/services/doc/doc.service';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { DocResponse } from 'src/app/core/interfaces/doc.interface';

@Component({
  selector: 'app-doc-schedule',
  templateUrl: './doc-schedule.component.html',
  styleUrls: ['./doc-schedule.component.scss'],
})
export class DocScheduleComponent implements OnInit {
  doctor?: DocResponse;

  docService = inject(DocService);
  activatedRoute = inject(ActivatedRoute);

  ngOnInit(): void {
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
}

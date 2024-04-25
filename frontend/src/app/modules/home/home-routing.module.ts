import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PrincipalComponent } from './principal/principal.component';
import { SearchDoctorComponent } from './search-doctor/search-doctor.component';
import { AppointmentFormComponent } from './appointment-form/appointment-form.component';
import { AuthGuard } from 'src/app/core/shared/guards/auth.guard';

import { DocScheduleComponent } from './doc-schedule/doc-schedule.component';
import { AppointmentConfirmationComponent } from './appointment-confirmation/appointment-confirmation.component';


const routes: Routes = [
  {
    path: '',
    children: [
      { path: '', component: PrincipalComponent },

      { path: 'search-doctor', component: SearchDoctorComponent },
      {
        path: 'appointment-form',
        component: AppointmentFormComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'appointment-confirmation',
        component: AppointmentConfirmationComponent,
        canActivate: [AuthGuard],
      },
      { path: 'scheduler/:id', component: DocScheduleComponent,
      canActivate: [AuthGuard], },
    ],
  },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomeRoutingModule {}

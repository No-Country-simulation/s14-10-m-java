import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PrincipalComponent } from './principal/principal.component';
import { SearchDoctorComponent } from './search-doctor/search-doctor.component';
import { AppointmentConfirmationComponent } from './appointment-confirmation/appointment-confirmation.component';

const routes: Routes = [
  {
    path: '',
    children: [
      { path: '', component: PrincipalComponent },
      { path: 'search-doctor', component: SearchDoctorComponent },
      { path: 'appointment-confirmation', component: AppointmentConfirmationComponent },

    ],
    

  },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }

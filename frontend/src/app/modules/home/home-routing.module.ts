import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PrincipalComponent } from './principal/principal.component';
import { SearchDoctorComponent } from './search-doctor/search-doctor.component';

const routes: Routes = [
  {
    path: '',
    children: [
      { path: '', component: PrincipalComponent },
      { path: 'search-doctor', component: SearchDoctorComponent },
    ],
    

  },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }

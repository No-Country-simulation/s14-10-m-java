import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { AuthGuard } from 'src/app/core/shared/guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    children: [
      { path: 'profile-patient', component: ProfileComponent, canActivate: [AuthGuard] }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashPatientRoutingModule { }

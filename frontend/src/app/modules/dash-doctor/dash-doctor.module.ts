import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashDoctorRoutingModule } from './dash-doctor-routing.module';
import { ProfileComponent } from './profile/profile.component';


@NgModule({
  declarations: [
    ProfileComponent
  ],
  imports: [
    CommonModule,
    DashDoctorRoutingModule
  ]
})
export class DashDoctorModule { }

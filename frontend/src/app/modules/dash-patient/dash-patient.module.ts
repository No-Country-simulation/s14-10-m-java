import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashPatientRoutingModule } from './dash-patient-routing.module';
import { ProfileComponent } from './profile/profile.component';


@NgModule({
  declarations: [
    ProfileComponent
  ],
  imports: [
    CommonModule,
    DashPatientRoutingModule
  ]
})
export class DashPatientModule { }
